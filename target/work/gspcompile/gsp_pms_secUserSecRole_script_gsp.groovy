import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_pms_secUserSecRole_script_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/secUserSecRole/_script.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createClosureForHtmlPart(1, 1)
invokeTag('access','sec',5,['url':("/secUserSecRole/delete")],1)
printHtmlPart(2)
expressionOut.print(roleId)
printHtmlPart(3)
expressionOut.print(createLink(controller:'secUserSecRole', action: 'create'))
printHtmlPart(4)
expressionOut.print(createLink(controller:'secUserSecRole', action: 'update'))
printHtmlPart(5)
expressionOut.print(createLink(controller: 'secUserSecRole', action: 'list'))
printHtmlPart(6)
expressionOut.print(createLink(controller: 'secUserSecRole', action:  'delete'))
printHtmlPart(7)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1500795758459L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
