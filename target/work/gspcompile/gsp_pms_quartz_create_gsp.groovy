import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_pms_quartz_create_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/quartz/_create.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('checkBox','g',82,['class':("form-control-static"),'name':("isActive"),'tabindex':("5"),'data-bind':("checked: quartz.isActive")],-1)
printHtmlPart(2)
invokeTag('checkBox','g',92,['class':("form-control-static"),'name':("isRunning"),'tabindex':("6"),'data-bind':("checked: quartz.isRunning")],-1)
printHtmlPart(3)
})
invokeTag('form','g',112,['name':("quartzForm"),'id':("quartzForm"),'class':("form-horizontal form-widgets"),'role':("form")],1)
printHtmlPart(4)
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
