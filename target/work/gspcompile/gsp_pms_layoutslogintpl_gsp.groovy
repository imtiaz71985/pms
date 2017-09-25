import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_pms_layoutslogintpl_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/layouts/logintpl.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',3,['gsp_sm_xmlClosingForEmptyTag':("/"),'http-equiv':("Content-Type"),'content':("text/html; charset=utf-8")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':("/"),'http-equiv':("cache-control"),'content':("public")],-1)
printHtmlPart(2)
expressionOut.print(createLinkTo(dir: 'images', file: 'favicon.ico'))
printHtmlPart(3)
createTagBody(2, {->
createClosureForHtmlPart(4, 3)
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(5)
expressionOut.print(resource(dir: 'css', file: 'bootstrap/css/bootstrap.min.css'))
printHtmlPart(6)
expressionOut.print(resource(dir: 'js', file: 'jquery/jquery-1.11.2.min.js'))
printHtmlPart(7)
expressionOut.print(resource(dir: 'js', file: 'bootstrap/bootstrap.min.js'))
printHtmlPart(8)
})
invokeTag('captureHead','sitemesh',18,[:],1)
printHtmlPart(9)
createTagBody(1, {->
printHtmlPart(10)
expressionOut.print(resource(dir: 'images', file: 'logo.png'))
printHtmlPart(11)
if((grails.util.Environment.current.name == 'development') && true) {
printHtmlPart(12)
expressionOut.print(postUrl)
printHtmlPart(13)
expressionOut.print(createLink(controller: 'login', action: 'sendPasswordResetLink'))
printHtmlPart(14)
}
printHtmlPart(15)
if((grails.util.Environment.current.name == 'production') && true) {
printHtmlPart(12)
expressionOut.print(postUrl)
printHtmlPart(16)
expressionOut.print(createLink(controller: 'login', action: 'sendPasswordResetLink'))
printHtmlPart(14)
}
printHtmlPart(17)
if(true && (flash.message && !flash.success)) {
printHtmlPart(18)
expressionOut.print(flash.message)
printHtmlPart(19)
}
printHtmlPart(20)
if(true && (flash.message && flash.success)) {
printHtmlPart(21)
expressionOut.print(flash.message)
printHtmlPart(19)
}
printHtmlPart(22)
})
invokeTag('captureBody','sitemesh',192,['class':("container-fluid"),'style':("background-color: #F8F8F8;")],1)
printHtmlPart(23)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1496313991375L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
