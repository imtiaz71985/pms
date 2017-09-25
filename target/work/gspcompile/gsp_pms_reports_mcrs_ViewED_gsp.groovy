import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_pms_reports_mcrs_ViewED_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/reports/mcrs/_ViewED.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
for( item in (list) ) {
printHtmlPart(1)
if(true && (!item?.is_heading)) {
printHtmlPart(2)
expressionOut.print(item?.id)
printHtmlPart(3)
expressionOut.print(item?.issue_name)
printHtmlPart(4)
expressionOut.print(item?.id)
printHtmlPart(5)
expressionOut.print(item?.description)
printHtmlPart(6)
expressionOut.print(item?.id)
printHtmlPart(5)
expressionOut.print(item?.remarks)
printHtmlPart(7)
expressionOut.print(item?.id)
printHtmlPart(5)
expressionOut.print(item?.ed_advice)
printHtmlPart(8)
}
else {
printHtmlPart(9)
expressionOut.print(item?.id)
printHtmlPart(3)
expressionOut.print(item?.issue_name)
printHtmlPart(10)
}
printHtmlPart(11)
}
printHtmlPart(12)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1485668637141L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
