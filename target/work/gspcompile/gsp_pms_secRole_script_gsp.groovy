import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_pms_secRole_script_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/secRole/_script.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createClosureForHtmlPart(1, 1)
invokeTag('access','sec',5,['url':("/secRole/create")],1)
printHtmlPart(2)
createClosureForHtmlPart(3, 1)
invokeTag('access','sec',8,['url':("/secRole/update")],1)
printHtmlPart(2)
createClosureForHtmlPart(4, 1)
invokeTag('access','sec',11,['url':("/secRole/delete")],1)
printHtmlPart(2)
createClosureForHtmlPart(5, 1)
invokeTag('access','sec',14,['url':("/secUserSecRole/show")],1)
printHtmlPart(6)
expressionOut.print(createLink(controller:'secRole', action: 'create'))
printHtmlPart(7)
expressionOut.print(createLink(controller:'secRole', action: 'update'))
printHtmlPart(8)
expressionOut.print(createLink(controller: 'secRole', action: 'list'))
printHtmlPart(9)
expressionOut.print(createLink(controller: 'secRole', action:  'delete'))
printHtmlPart(10)
expressionOut.print(createLink(controller: 'secUserSecRole', action: 'show'))
printHtmlPart(11)
expressionOut.print(createLink(controller: 'secUserSecRole', action: 'report'))
printHtmlPart(12)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1500795758436L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
