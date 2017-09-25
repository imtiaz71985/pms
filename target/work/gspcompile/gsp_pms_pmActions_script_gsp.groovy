import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_pms_pmActions_script_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/pmActions/_script.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createClosureForHtmlPart(1, 1)
invokeTag('access','sec',5,['url':("/pmActions/create")],1)
printHtmlPart(2)
createClosureForHtmlPart(3, 1)
invokeTag('access','sec',8,['url':("/pmActions/update")],1)
printHtmlPart(2)
createClosureForHtmlPart(4, 1)
invokeTag('access','sec',11,['url':("/pmActions/delete")],1)
printHtmlPart(5)
expressionOut.print(createLink(controller: 'pmActions', action: 'list'))
printHtmlPart(6)
expressionOut.print(isSubmitted)
printHtmlPart(7)
expressionOut.print(serviceId)
printHtmlPart(8)
expressionOut.print(lstService)
printHtmlPart(9)
expressionOut.print(lstProject)
printHtmlPart(10)
expressionOut.print(serviceId)
printHtmlPart(11)
expressionOut.print(isAdmin)
printHtmlPart(12)
expressionOut.print(createLink(controller:'systemEntity', action: 'unitsByType'))
printHtmlPart(13)
expressionOut.print(createLink(controller:'pmActions', action: 'create'))
printHtmlPart(14)
expressionOut.print(createLink(controller:'pmActions', action: 'update'))
printHtmlPart(15)
expressionOut.print(createLink(controller: 'pmActions', action:  'delete'))
printHtmlPart(16)
expressionOut.print(createLink(controller: 'pmActions', action: 'listIndicatorByActions'))
printHtmlPart(17)
expressionOut.print(createLink(controller:'pmActions', action: 'listDetailsByIndicator'))
printHtmlPart(18)
expressionOut.print(createLink(controller:'pmActions', action: 'updateIndicatorDetails'))
printHtmlPart(19)
if(true && (isAdmin)) {
printHtmlPart(20)
}
printHtmlPart(21)
expressionOut.print(createLink(controller: 'pmActions', action: 'listIndicatorByActions'))
printHtmlPart(22)
expressionOut.print(createLink(controller: 'pmActions', action: 'listDetailsByIndicator'))
printHtmlPart(23)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1503566292051L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
