import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_pms_meetingLog_create_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/meetingLog/_create.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(meetingType)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
if(true && (meetingType == 'Monthly')) {
printHtmlPart(3)
createClosureForHtmlPart(4, 3)
invokeTag('dropDownMeetingCategory','app',33,['class':("kendo-drop-down"),'id':("meetingCatId"),'name':("meetingCatId"),'tabindex':("1"),'meeting_type':(meetingType),'onchange':("dropDownCategoryChange()"),'data-bind':("value: meetingLog.meetingCatId"),'data_model_name':("dropDownCategory")],3)
printHtmlPart(5)
}
printHtmlPart(6)
createClosureForHtmlPart(7, 2)
invokeTag('dateControl','app',44,['name':("heldOn"),'tabindex':("2"),'class':("kendo-date-picker"),'data-bind':("value: meetingLog.heldOn"),'placeholder':("Held On")],2)
printHtmlPart(8)
expressionOut.print(meetingType == 'Monthly'?2:4)
printHtmlPart(9)
})
invokeTag('form','g',115,['name':("meetingLogForm"),'id':("meetingLogForm"),'class':("form-horizontal form-widgets"),'role':("form")],1)
printHtmlPart(10)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1504689397957L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
