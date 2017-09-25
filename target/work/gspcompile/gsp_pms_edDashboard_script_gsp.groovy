import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_pms_edDashboard_script_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/edDashboard/_script.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(subDate)
printHtmlPart(1)
expressionOut.print(serviceId)
printHtmlPart(2)
expressionOut.print(subDate)
printHtmlPart(3)
expressionOut.print(createLink(controller:'edDashboard', action: 'lastSubDateByService'))
printHtmlPart(4)
expressionOut.print(createLink(controller: 'edDashboard', action: 'list'))
printHtmlPart(5)
expressionOut.print(createLink(controller:'edDashboard', action: 'unresolveList'))
printHtmlPart(6)
expressionOut.print(createLink(controller: 'edDashboard', action:  'delete'))
printHtmlPart(7)
expressionOut.print(createLink(controller:'edDashboard', action: 'retrieveIssueAndMonthData'))
printHtmlPart(8)
expressionOut.print(createLink(controller:'edDashboard', action: 'create'))
printHtmlPart(9)
expressionOut.print(createLink(controller:'edDashboard', action: 'update'))
printHtmlPart(10)
expressionOut.print(createLink(controller:'edDashboard', action: 'retrieveIssueAndMonthData'))
printHtmlPart(11)
expressionOut.print(createLink(controller:'edDashboard', action: 'update'))
printHtmlPart(12)
expressionOut.print(createLink(controller: 'edDashboard', action: 'resolvedList'))
printHtmlPart(13)
expressionOut.print(createLink(controller: 'edDashboard', action: 'upcomingFollowupList'))
printHtmlPart(14)
expressionOut.print(createLink(controller: 'edDashboard', action: 'resolvedList'))
printHtmlPart(15)
expressionOut.print(createLink(controller: 'edDashboard', action: 'upcomingFollowupList'))
printHtmlPart(16)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1505888084891L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
