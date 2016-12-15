package pms

import actions.pmMissions.CreatePmMissionsActionService
import actions.pmMissions.DeletePmMissionsActionService
import actions.pmMissions.ListPmMissionsActionService
import actions.pmMissions.UpdatePmMissionsActionService
import com.pms.SecUser
import grails.converters.JSON

class PmMissionsController  extends BaseController {

    static allowedMethods = [
            show: "POST", create: "POST", update: "POST",delete: "POST", list: "POST"
    ]

    BaseService baseService
    CreatePmMissionsActionService createPmMissionsActionService
    UpdatePmMissionsActionService updatePmMissionsActionService
    DeletePmMissionsActionService deletePmMissionsActionService
    ListPmMissionsActionService listPmMissionsActionService

    def show() {
        SecUser user = baseService.currentUserObject()
        render(view: "/pmMissions/show", model: [serviceId:user.serviceId])
    }
    def create() {
        renderOutput(createPmMissionsActionService, params)

    }
    def update() {
        renderOutput(updatePmMissionsActionService, params)

    }
    def delete() {
        renderOutput(deletePmMissionsActionService, params)

    }
    def list() {
        renderOutput(listPmMissionsActionService, params)
    }
}
