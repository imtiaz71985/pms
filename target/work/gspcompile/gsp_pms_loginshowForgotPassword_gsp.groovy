import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_pms_loginshowForgotPassword_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/login/showForgotPassword.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',3,['gsp_sm_xmlClosingForEmptyTag':("/"),'http-equiv':("Content-Type"),'content':("text/html; charset=utf-8")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':("/"),'http-equiv':("cache-control"),'content':("public")],-1)
printHtmlPart(1)
createTagBody(2, {->
createClosureForHtmlPart(2, 3)
invokeTag('captureTitle','sitemesh',5,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',5,[:],2)
printHtmlPart(3)
expressionOut.print(createLinkTo(dir: 'images', file: 'favicon.png'))
printHtmlPart(4)
expressionOut.print(resource(dir: 'css', file: 'bootstrap/css/bootstrap.min.css'))
printHtmlPart(5)
expressionOut.print(resource(dir: 'js', file: 'jquery/jquery-1.11.2.min.js'))
printHtmlPart(6)
expressionOut.print(resource(dir: 'js', file: 'bootstrap/bootstrap.min.js'))
printHtmlPart(7)
})
invokeTag('captureHead','sitemesh',17,[:],1)
printHtmlPart(8)
createTagBody(1, {->
printHtmlPart(9)
expressionOut.print(createLink(controller: 'login', action: 'managePassword'))
printHtmlPart(10)
expressionOut.print(userInfoMap?.passwordResetLink)
printHtmlPart(11)
expressionOut.print(userInfoMap?.username)
printHtmlPart(12)
expressionOut.print(userInfoMap?.empname)
printHtmlPart(13)
expressionOut.print(userInfoMap?.username)
printHtmlPart(14)
expressionOut.print(createLink(controller: 'login', action: 'auth'))
printHtmlPart(15)
if(true && (flash.message && !flash.success)) {
printHtmlPart(16)
expressionOut.print(flash.message)
printHtmlPart(17)
}
printHtmlPart(18)
if(true && (flash.message && flash.success)) {
printHtmlPart(19)
expressionOut.print(flash.message)
printHtmlPart(17)
}
printHtmlPart(20)
})
invokeTag('captureBody','sitemesh',113,[:],1)
printHtmlPart(21)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1490756084258L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
