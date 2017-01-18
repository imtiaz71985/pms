package pms

import actions.reports.DownloadActionsIndicatorSPActionService
import actions.reports.DownloadMCRSActionService
import actions.reports.ListActionsIndicatorActionService
import actions.reports.ListMCRSActionService
import actions.reports.monthly.DownloadMonthlySPActionService
import actions.reports.monthly.ListSpMonthlyPlanActionService
import actions.reports.ListSpPlanActionService
import actions.reports.yearly.DownloadYearlySPActionService
import actions.reports.yearly.ListYearlySPActionService
import com.pms.PmServiceSector
import com.pms.SecUser
import grails.converters.JSON
import groovy.sql.GroovyRowResult
import service.PmActionsService

class ReportsController  extends BaseController  {
    BaseService baseService
    PmActionsService pmActionsService
    ListSpPlanActionService listSpPlanActionService
    ListActionsIndicatorActionService listActionsIndicatorActionService
    DownloadActionsIndicatorSPActionService downloadActionsIndicatorSPActionService

    ListMCRSActionService listMCRSActionService
    DownloadMCRSActionService downloadMCRSActionService

    ListSpMonthlyPlanActionService listSpMonthlyPlanActionService
    DownloadMonthlySPActionService downloadMonthlySPActionService

    ListYearlySPActionService listYearlySPActionService
    DownloadYearlySPActionService downloadYearlySPActionService

    static allowedMethods = [
            showSpPlan: "POST", listSpPlan: "POST", showMcrs: "POST"
    ]
    def showSpPlan() {
        SecUser user = baseService.currentUserObject()
        PmServiceSector service = PmServiceSector.read(user.serviceId)
        render(view: "/reports/yearly/show", model: [serviceId:service.id,serviceName:service.name])
    }
    def listSpPlan() {
        renderOutput(listSpPlanActionService,params)
    }
    def showSpStatus() {
        List<GroovyRowResult> lst = pmActionsService.lstDepartmentSpStatus()
        render(view: "/reports/statistical/show",model: [lst: lst as JSON])
    }


    /////////////Monthly Start/////////////////////
    def showSpMonthlyPlan() {
        SecUser user = baseService.currentUserObject()
        render(view: "/reports/monthly/show", model: [serviceId:user.serviceId])
    }
    def listSpMonthlyPlan() {
        renderOutput(listSpMonthlyPlanActionService,params)
    }
    def downloadMonthlySP() {
        Map result = (Map) getReportResponse(downloadMonthlySPActionService, params).report
        renderOutputStream(result.report.toByteArray(), result.format, result.reportFileName)
    }
    /////////////Monthly End/////////////////////


    /////////////Yearly Start/////////////////////
    def showYearlySP() {
        render(view: "/reports/yearly/show")
    }
    def listYearlySP() {
        renderOutput(listYearlySPActionService,params)
    }
    def downloadYearlySP() {
        Map result = (Map) getReportResponse(downloadYearlySPActionService, params).report
        renderOutputStream(result.report.toByteArray(), result.format, result.reportFileName)
    }
    /////////////Yearly End/////////////////////

    /////////////MCRS Start/////////////////////
    def showMcrs() {
        List<GroovyRowResult> lst = pmActionsService.lstDepartmentSpStatus()
        render(view: "/reports/mcrs/show",model: [lst: lst as JSON])
    }
    def listMcrs() {
        renderOutput(listMCRSActionService,params)
    }
    def downloadMcrs() {
        Map result = (Map) getReportResponse(downloadMCRSActionService, params).report
        renderOutputStream(result.report.toByteArray(), result.format, result.reportFileName)
    }
    /////////////MCRS End/////////////////////

    def showActionsIndicator() {
        List<GroovyRowResult> lst = pmActionsService.lstDepartmentSpStatus()
        render(view: "/reports/strategicPlan/actionsIndicator/show",model: [lst: lst as JSON])
    }
    def listActionsIndicator() {
        renderOutput(listActionsIndicatorActionService,params)
    }
    def downloadActionsIndicator() {
        Map result = (Map) getReportResponse(downloadActionsIndicatorSPActionService, params).report
        renderOutputStream(result.report.toByteArray(), result.format, result.reportFileName)
    }
}
