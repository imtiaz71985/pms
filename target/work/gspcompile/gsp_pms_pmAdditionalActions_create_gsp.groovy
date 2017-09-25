import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_pms_pmAdditionalActions_create_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/pmAdditionalActions/_create.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
createClosureForHtmlPart(2, 2)
invokeTag('dropDownService','app',31,['class':("kendo-drop-down"),'readonly':("true"),'onchange':("javascript:populateGoals();"),'id':("serviceId"),'name':("serviceId"),'data-bind':("value: actions.serviceId"),'data_model_name':("dropDownService")],2)
printHtmlPart(3)
createClosureForHtmlPart(2, 2)
invokeTag('dropDownGoals','app',45,['class':("kendo-drop-down"),'id':("goalId"),'name':("goalId"),'placeholder':("Select Goal"),'tabindex':("1"),'data-bind':("value: actions.goalId"),'data_model_name':("dropDownGoals")],2)
printHtmlPart(4)
createClosureForHtmlPart(2, 2)
invokeTag('dropDownEmployee','app',79,['data_model_name':("dropDownEmployee"),'required':("false"),'class':("kendo-drop-down"),'sort_by_department':("true"),'tabindex':("5"),'id':("resPersonId"),'name':("resPersonId"),'data-bind':("value: actions.resPersonId")],2)
printHtmlPart(5)
})
invokeTag('form','g',168,['name':("additionalMRPForm"),'id':("additionalMRPForm"),'class':("form-horizontal form-widgets"),'role':("form")],1)
printHtmlPart(6)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1501669019687L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
