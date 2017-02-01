package pms

import com.pms.PmServiceSector
import com.pms.SecUser
import grails.converters.JSON
import grails.plugin.springsecurity.SpringSecurityUtils
import groovy.sql.GroovyRowResult
import org.springframework.security.authentication.AccountExpiredException
import org.springframework.security.authentication.CredentialsExpiredException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.LockedException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.session.SessionInformation
import org.springframework.security.web.WebAttributes
import service.PmActionsService

import javax.servlet.http.HttpServletResponse

class LoginController {

    BaseService baseService

    PmActionsService pmActionsService

    def SessionRegistry

    /**
     * Dependency injection for the authenticationTrustResolver.
     */
    def authenticationTrustResolver

    /**
     * Dependency injection for the springSecurityService.
     */
    def springSecurityService

    /**
     * Default action; redirects to 'defaultTargetUrl' if logged in, /login/auth otherwise.
     */
    def index() {
        if (springSecurityService.isLoggedIn()) {
            redirect uri: SpringSecurityUtils.securityConfig.successHandler.defaultTargetUrl
        } else {
            redirect action: 'auth', params: params
        }
    }

    /**
     * Show the login page.
     */
    def auth() {
        def config = SpringSecurityUtils.securityConfig
        if (springSecurityService.isLoggedIn()) {
            redirect uri: config.successHandler.defaultTargetUrl
            return
        }
        String view = 'auth'
        String postUrl = "${request.contextPath}${config.apf.filterProcessesUrl}"
        render view: view, model: [postUrl            : postUrl,
                                   rememberMeParameter: config.rememberMe.parameter]
    }

    def loginSuccess() {
        String url = '/'
        redirect(uri: url)
    }

    /**
     * The redirect action for Ajax requests.
     */
    def authAjax() {
        response.setHeader 'Location', SpringSecurityUtils.securityConfig.auth.ajaxLoginFormUrl
        response.sendError HttpServletResponse.SC_UNAUTHORIZED
    }

    /**
     * Show denied page.
     */
    def denied() {
        if (springSecurityService.isLoggedIn() &&
                authenticationTrustResolver.isRememberMe(SecurityContextHolder.context?.authentication)) {
            // have cookie but the page is guarded with IS_AUTHENTICATED_FULLY
            redirect action: 'full', params: params
        }
    }

    /**
     * Login page for users with a remember-me cookie but accessing a IS_AUTHENTICATED_FULLY page.
     */
    def full() {
        def config = SpringSecurityUtils.securityConfig
        render view: 'auth', params: params,
                model: [hasCookie: authenticationTrustResolver.isRememberMe(SecurityContextHolder.context?.authentication),
                        postUrl  : "${request.contextPath}${config.apf.filterProcessesUrl}"]
    }

    /**
     * Callback after a failed login. Redirects to the auth page with a warning message.
     */
    def authfail() {

        String msg = ''
        def exception = session[WebAttributes.AUTHENTICATION_EXCEPTION]
        if (exception) {
            if (exception instanceof AccountExpiredException) {
                msg = g.message(code: "springSecurity.errors.login.expired")
            } else if (exception instanceof CredentialsExpiredException) {
                msg = g.message(code: "springSecurity.errors.login.passwordExpired")
            } else if (exception instanceof DisabledException) {
                msg = g.message(code: "springSecurity.errors.login.disabled")
            } else if (exception instanceof LockedException) {
                msg = g.message(code: "springSecurity.errors.login.locked")
            } else {
                msg = g.message(code: "springSecurity.errors.login.fail")
            }
        }

        if (springSecurityService.isAjax(request)) {
            render([error: msg] as JSON)
        } else {
            flash.message = msg
            redirect action: 'auth', params: params
        }
    }

    /**
     * The Ajax success redirect url.
     */
    def ajaxSuccess() {
        render([success: true, username: springSecurityService.authentication.name] as JSON)
    }

    /**
     * The Ajax denied redirect url.
     */
    def ajaxDenied() {
        render([error: 'access denied'] as JSON)
    }

    def dashBoard() {
        render(template: "../layouts/dashBoard")
    }
    def lstUserDashboard() {
        List<GroovyRowResult> lst = pmActionsService.lstGoalWiseActionStatus(params.month.toString())
        render lst as JSON
    }
    def lstManagementDashboard() {
        List<GroovyRowResult> lst = pmActionsService.lstServiceWiseActionStatus(params.month.toString())
        render lst as JSON
    }

    def resetPassword() {
        SecUser user = SecUser.findByUsername(springSecurityService.authentication.name)
        render(view: '/login/resetPassword', model: [userId: user.id])
    }


    def showOnlineUser() {
        render(view: '/whoIsOnline/showOnlineUser')
    }

    def listOnlineUser() {
        SecUser cUser = baseService.currentUserObject()
        boolean isAdmin = baseService.isUserSystemAdmin(cUser.id)
        List lstUsers = []
        sessionRegistry.getAllPrincipals().each {
            SecUser user = SecUser.findByUsername(it.username)
            String department = ""
            if (user.username == "admin") {
                department = "<b>Software Development</b>"
            } else {
                PmServiceSector service = PmServiceSector.read(user.serviceId)
                department = service.name
            }
            List<SessionInformation> lst = sessionRegistry.getAllSessions(it, true)
            if (!isAdmin) {
                if (user.serviceId == cUser.serviceId) {
                    Map eachDetails = [
                            id          : user.id,
                            username    : user.username,
                            employeeName: user.fullName,
                            department  : department,
                            signInTime  : lst.lastRequest[0]
                    ]
                    lstUsers << eachDetails
                }
            } else {
                Map eachDetails = [
                        id          : user.id,
                        username    : user.username,
                        employeeName: user.fullName,
                        department  : department,
                        signInTime  : lst.lastRequest[0]
                ]
                lstUsers << eachDetails
            }
        }
        Map result = new LinkedHashMap()
        result.put("list", lstUsers)
        result.put("count", lstUsers.size())
        String output = result as JSON

        render output
    }
}
