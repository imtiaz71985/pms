import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_pms_pmAdditionalActions_script_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/pmAdditionalActions/_script.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createClosureForHtmlPart(1, 1)
invokeTag('access','sec',5,['url':("/pmAdditionalActions/create")],1)
printHtmlPart(2)
createClosureForHtmlPart(3, 1)
invokeTag('access','sec',8,['url':("/pmAdditionalActions/update")],1)
printHtmlPart(4)
createClosureForHtmlPart(5, 1)
invokeTag('access','sec',11,['url':("/pmAdditionalActions/delete")],1)
printHtmlPart(6)
expressionOut.print(submissionDate)
printHtmlPart(7)
expressionOut.print(submissionDate)
printHtmlPart(8)
expressionOut.print(submissionDate)
printHtmlPart(9)
expressionOut.print(submissionDate)
printHtmlPart(10)
expressionOut.print(lstService)
printHtmlPart(11)
expressionOut.print(lstProject)
printHtmlPart(12)
expressionOut.print(serviceId)
printHtmlPart(13)
expressionOut.print(createLink(controller:'systemEntity', action: 'unitsByType'))
printHtmlPart(14)
expressionOut.print(createLink(controller:'pmAdditionalActions', action: 'create'))
printHtmlPart(15)
expressionOut.print(createLink(controller:'pmAdditionalActions', action: 'update'))
printHtmlPart(16)
expressionOut.print(createLink(controller: 'pmAdditionalActions', action: 'list'))
printHtmlPart(17)
expressionOut.print(createLink(controller: 'pmAdditionalActions', action: 'listIndicatorByActions'))
printHtmlPart(18)
expressionOut.print(createLink(controller: 'pmAdditionalActions', action:  'delete'))
printHtmlPart(19)
expressionOut.print(createLink(controller: 'pmAdditionalActions', action: 'listIndicatorByActions'))
printHtmlPart(20)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1501669180280L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
