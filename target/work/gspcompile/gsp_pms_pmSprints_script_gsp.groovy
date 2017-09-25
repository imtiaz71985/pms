import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_pms_pmSprints_script_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/pmSprints/_script.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createClosureForHtmlPart(1, 1)
invokeTag('access','sec',5,['url':("/pmSprints/create")],1)
printHtmlPart(2)
createClosureForHtmlPart(3, 1)
invokeTag('access','sec',8,['url':("/pmSprints/update")],1)
printHtmlPart(2)
createClosureForHtmlPart(4, 1)
invokeTag('access','sec',11,['url':("/pmSprints/delete")],1)
printHtmlPart(5)
expressionOut.print(createLink(controller:'pmSprints', action: 'create'))
printHtmlPart(6)
expressionOut.print(createLink(controller:'pmSprints', action: 'update'))
printHtmlPart(7)
expressionOut.print(createLink(controller: 'pmSprints', action: 'list'))
printHtmlPart(8)
expressionOut.print(createLink(controller: 'pmSprints', action:  'delete'))
printHtmlPart(9)
expressionOut.print(createLink(controller: 'pmGoals', action: 'lstGoalsByServiceId'))
printHtmlPart(10)
expressionOut.print(createLink(controller: 'pmActions', action: 'lstActionsByGoalId'))
printHtmlPart(11)
expressionOut.print(createLink(controller:'pmActions', action: 'actionsStartAndEndDateById'))
printHtmlPart(12)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1500795758454L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
