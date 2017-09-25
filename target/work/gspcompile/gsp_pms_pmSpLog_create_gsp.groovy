import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_pms_pmSpLog_create_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/pmSpLog/_create.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
createClosureForHtmlPart(2, 2)
invokeTag('dropDownService','app',26,['class':("kendo-drop-down"),'required':("true"),'validationMessage':("Required"),'id':("serviceId"),'name':("serviceId"),'tabindex':("1"),'data-bind':("value: spLog.serviceId"),'data_model_name':("dropDownService")],2)
printHtmlPart(3)
invokeTag('checkBox','g',41,['class':("form-control-static"),'id':("isSubmitted"),'name':("isSubmitted"),'tabindex':("3"),'data-bind':("checked: spLog.isSubmitted"),'onchange':("checkSubmitted();")],-1)
printHtmlPart(4)
invokeTag('checkBox','g',48,['class':("form-control-static"),'id':("isEditable"),'name':("isEditable"),'tabindex':("4"),'onchange':("checkEditable();"),'data-bind':("checked: spLog.isEditable")],-1)
printHtmlPart(5)
})
invokeTag('form','g',68,['name':("spLogForm"),'id':("spLogForm"),'class':("form-horizontal form-widgets"),'role':("form")],1)
printHtmlPart(6)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1486265164357L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
