import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_pms_reports_statisticalshowMcrsDetails_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/reports/statistical/showMcrsDetails.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(typeStr)
printHtmlPart(1)
expressionOut.print(month)
printHtmlPart(2)
expressionOut.print(service)
printHtmlPart(3)
for( log in (result) ) {
printHtmlPart(4)
expressionOut.print(log?.sl)
printHtmlPart(5)
expressionOut.print(log?.editable_on?log.editable_on:logStart)
printHtmlPart(5)
expressionOut.print(log?.submitted_on)
printHtmlPart(5)
expressionOut.print(log?.is_current?'YES':'NO')
printHtmlPart(6)
}
printHtmlPart(7)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1495086185100L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
