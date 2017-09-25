import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_pms_reports_dashboard_scripts_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/reports/dashboard/_scripts.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(isSysAdmin)
printHtmlPart(1)
expressionOut.print(isTopMan)
printHtmlPart(1)
expressionOut.print(isSpAdmin)
printHtmlPart(1)
expressionOut.print(isMultiDept)
printHtmlPart(2)
expressionOut.print(serviceId)
printHtmlPart(3)
if(true && (isAssist)) {
printHtmlPart(4)
}
printHtmlPart(5)
if(true && (isAssist)) {
printHtmlPart(4)
}
printHtmlPart(6)
if(true && (isAssist)) {
printHtmlPart(4)
}
printHtmlPart(7)
if(true && (isAssist)) {
printHtmlPart(4)
}
printHtmlPart(8)
if(true && (isAssist)) {
printHtmlPart(4)
}
printHtmlPart(9)
if(true && (isAssist)) {
printHtmlPart(4)
}
printHtmlPart(10)
expressionOut.print(createLink(controller: 'reports', action: 'listEdDashBoard'))
printHtmlPart(11)
expressionOut.print(createLink(controller: 'reports', action: 'listEdDashBoard'))
printHtmlPart(12)
expressionOut.print(createLink(controller: 'reports', action: 'listEdDashBoard'))
printHtmlPart(13)
expressionOut.print(createLink(controller: 'reports', action: 'listEdDashBoard'))
printHtmlPart(14)
expressionOut.print(createLink(controller: 'reports', action: 'listEdDashBoard'))
printHtmlPart(15)
expressionOut.print(createLink(controller: 'reports', action: 'listEdDashBoard'))
printHtmlPart(16)
expressionOut.print(createLink(controller: 'reports', action: 'listEdDashBoard'))
printHtmlPart(17)
expressionOut.print(createLink(controller: 'reports', action: 'listEdDashBoard'))
printHtmlPart(18)
expressionOut.print(createLink(controller:'edDashboard', action: 'update'))
printHtmlPart(19)
expressionOut.print(createLink(controller:'edDashboard', action: 'retrieveIssueAndMonthData'))
printHtmlPart(20)
expressionOut.print(createLink(controller: 'reports', action:  'downloadEdDashBoard'))
printHtmlPart(21)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1499573043859L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
