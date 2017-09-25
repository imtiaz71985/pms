import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_pms_quartz_script_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/quartz/_script.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createClosureForHtmlPart(1, 1)
invokeTag('access','sec',5,['url':("/quartz/create")],1)
printHtmlPart(2)
createClosureForHtmlPart(3, 1)
invokeTag('access','sec',8,['url':("/quartz/update")],1)
printHtmlPart(2)
createClosureForHtmlPart(4, 1)
invokeTag('access','sec',11,['url':("/quartz/delete")],1)
printHtmlPart(2)
createClosureForHtmlPart(5, 1)
invokeTag('access','sec',14,['url':("/quartz/startScheduler")],1)
printHtmlPart(2)
createClosureForHtmlPart(6, 1)
invokeTag('access','sec',17,['url':("/quartz/stopScheduler")],1)
printHtmlPart(7)
expressionOut.print(createLink(controller:'quartz', action: 'create'))
printHtmlPart(8)
expressionOut.print(createLink(controller:'quartz', action: 'update'))
printHtmlPart(9)
expressionOut.print(createLink(controller: 'quartz', action: 'list'))
printHtmlPart(10)
expressionOut.print(createLink(controller: 'quartz', action:  'delete'))
printHtmlPart(11)
expressionOut.print(createLink(controller: 'quartz', action:  'startScheduler'))
printHtmlPart(12)
expressionOut.print(createLink(controller: 'quartz', action:  'stopScheduler'))
printHtmlPart(13)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1490756084323L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
