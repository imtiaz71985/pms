package pms

import actions.pmActions.CreatePmActionsActionService
import actions.pmActions.DeletePmActionsActionService
import actions.pmActions.ListPmActionsActionService
import actions.pmActions.UpdatePmActionsActionService
import com.pms.PmActions
import com.pms.PmServiceSector
import grails.converters.JSON
import groovy.sql.GroovyRowResult
import service.PmActionsService
import service.PmServiceSectorService

class PmActionsController extends BaseController {

    static allowedMethods = [
            show: "POST", create: "POST", update: "POST",delete: "POST", list: "POST"
    ]
    BaseService baseService
    PmActionsService pmActionsService
    PmServiceSectorService pmServiceSectorService
    CreatePmActionsActionService createPmActionsActionService
    UpdatePmActionsActionService updatePmActionsActionService
    DeletePmActionsActionService deletePmActionsActionService
    ListPmActionsActionService listPmActionsActionService

    def show() {
        List<PmServiceSector> lst = pmServiceSectorService.activeList()
        lst.remove(0)
        render(view: "/pmActions/show", model: [lstService: lst as JSON])
    }
    def create() {
        renderOutput(createPmActionsActionService, params)

    }
    def update() {
        renderOutput(updatePmActionsActionService, params)

    }
    def delete() {
        renderOutput(deletePmActionsActionService, params)

    }
    def list() {
        renderOutput(listPmActionsActionService, params)
    }
    def lstActionsByObjectiveId() {
        long objectiveId = Long.parseLong(params.objectiveId.toString())
        List<GroovyRowResult> lst = pmActionsService.lstActionsForDropDown(objectiveId)
        List lstValue = baseService.listForKendoDropdown(lst, null, null)
                Map result = [lstActions: lstValue]
                render result as JSON
    }
}
