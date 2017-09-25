import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_pms_edDashboard_table_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/edDashboard/_table.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
for( item in (list) ) {
printHtmlPart(1)
invokeTag('hiddenField','g',17,['name':("hfIsFollowup${item?.id}"),'value':(item?.is_followup)],-1)
printHtmlPart(1)
invokeTag('hiddenField','g',18,['name':("hfIsResolve${item?.id}"),'value':(item?.is_resolve)],-1)
printHtmlPart(1)
invokeTag('hiddenField','g',19,['name':("hfFollowupMonthFor${item?.id}"),'value':(item?.followup_month_for)],-1)
printHtmlPart(2)
expressionOut.print(item?.id)
printHtmlPart(3)
expressionOut.print(item?.id)
printHtmlPart(4)
expressionOut.print(item?.issue_name)
printHtmlPart(5)
expressionOut.print(item?.id)
printHtmlPart(6)
expressionOut.print(item?.id)
printHtmlPart(7)
expressionOut.print(item?.issuedMonthStr)
printHtmlPart(8)
expressionOut.print(item?.id)
printHtmlPart(9)
expressionOut.print(item?.id)
printHtmlPart(10)
expressionOut.print(item?.id)
printHtmlPart(11)
expressionOut.print(item?.description)
printHtmlPart(12)
expressionOut.print(item?.id)
printHtmlPart(13)
expressionOut.print(item?.id)
printHtmlPart(14)
expressionOut.print(item?.id)
printHtmlPart(15)
expressionOut.print(item?.remarks)
printHtmlPart(16)
expressionOut.print(item?.id)
printHtmlPart(17)
expressionOut.print(item?.id)
printHtmlPart(18)
expressionOut.print(item?.issue_status)
printHtmlPart(19)
}
printHtmlPart(20)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1496732116104L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
