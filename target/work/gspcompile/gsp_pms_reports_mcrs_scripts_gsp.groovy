import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_pms_reports_mcrs_scripts_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/reports/mcrs/_scripts.gsp" }
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
expressionOut.print(isSysAdmin)
printHtmlPart(1)
expressionOut.print(isTopMan)
printHtmlPart(1)
expressionOut.print(isSpAdmin)
printHtmlPart(1)
expressionOut.print(isMultiDept)
printHtmlPart(3)
expressionOut.print(serviceId)
printHtmlPart(4)
expressionOut.print(isTopMan)
printHtmlPart(5)
expressionOut.print(isSysAdmin)
printHtmlPart(6)
expressionOut.print(submissionDate)
printHtmlPart(7)
expressionOut.print(createLink(controller: 'login', action: 'listIndicatorLight'))
printHtmlPart(8)
expressionOut.print(createLink(controller: 'pmActions', action: 'updatePreference'))
printHtmlPart(9)
expressionOut.print(createLink(controller: 'reports', action: 'listMcrs'))
printHtmlPart(10)
expressionOut.print(createLink(controller: 'edDashboard', action: 'list'))
printHtmlPart(11)
expressionOut.print(createLink(controller: 'reports', action:  'downloadMcrs'))
printHtmlPart(12)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1505025562355L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
