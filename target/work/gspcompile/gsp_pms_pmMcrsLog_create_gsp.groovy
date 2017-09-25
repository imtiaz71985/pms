import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_pms_pmMcrsLog_create_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/pmMcrsLog/_create.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
createClosureForHtmlPart(2, 2)
invokeTag('dropDownService','app',25,['class':("kendo-drop-down"),'onchange':("populateGrid();"),'required':("true"),'validationMessage':("Required"),'id':("serviceId"),'name':("serviceId"),'tabindex':("1"),'data-bind':("value: mcrsLog.serviceId"),'data_model_name':("dropDownService")],2)
printHtmlPart(3)
invokeTag('checkBox','g',32,['class':("form-control-static"),'id':("isEditable"),'name':("isEditable"),'tabindex':("3"),'data-bind':("checked: mcrsLog.isEditable")],-1)
printHtmlPart(4)
invokeTag('checkBox','g',49,['class':("form-control-static"),'id':("isEditableDb"),'name':("isEditableDb"),'tabindex':("4"),'data-bind':("checked: mcrsLog.isEditableDb")],-1)
printHtmlPart(5)
})
invokeTag('form','g',70,['name':("mcrsLogForm"),'id':("mcrsLogForm"),'class':("form-horizontal form-widgets"),'role':("form")],1)
printHtmlPart(6)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1495086185092L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
