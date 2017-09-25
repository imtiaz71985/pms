import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_pms_pmActions_mrp_scripts_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/pmActions/mrp/_scripts.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(submissionDate)
printHtmlPart(1)
expressionOut.print(serviceId)
printHtmlPart(2)
expressionOut.print(submissionDate)
printHtmlPart(3)
expressionOut.print(createLink(controller:'pmActions', action: 'lastSubDateByService'))
printHtmlPart(4)
expressionOut.print(createLink(controller: 'pmActions', action: 'listAchievement'))
printHtmlPart(5)
expressionOut.print(createLink(controller: 'pmActions', action: 'updateAchievement'))
printHtmlPart(6)
if(true && (!isAdmin)) {
printHtmlPart(7)
}
printHtmlPart(8)
expressionOut.print(createLink(controller: 'pmActions', action: 'listAchievement'))
printHtmlPart(9)
expressionOut.print(createLink(controller: 'pmActions', action: 'listAchievement'))
printHtmlPart(10)
expressionOut.print(createLink(controller:'pmActions', action: 'updateExtendedTimeAchievement'))
printHtmlPart(11)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1504772357331L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
