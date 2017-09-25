import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_pms_reports_meetingLogshowMeetingDetails_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/reports/meetingLog/showMeetingDetails.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(meetingType)
printHtmlPart(1)
expressionOut.print(resultSet.service)
printHtmlPart(2)
expressionOut.print(resultSet.meeting_type)
printHtmlPart(3)
expressionOut.print(resultSet.meeting_cat)
printHtmlPart(4)
expressionOut.print(resultSet.held_on)
printHtmlPart(5)
expressionOut.print(resultSet.attendees_str)
printHtmlPart(6)
expressionOut.print(resultSet.issues)
printHtmlPart(7)
expressionOut.print(resultSet.log_str)
printHtmlPart(8)
expressionOut.print(resultSet.desc_str)
printHtmlPart(9)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1499826309309L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
