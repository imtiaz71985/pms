package pms

import actions.meetingLog.CreateMeetingLogActionService
import actions.meetingLog.DeleteMeetingLogActionService
import actions.meetingLog.ListMeetingLogActionService
import actions.meetingLog.UpdateMeetingLogActionService
import com.pms.PmServiceSector
import com.pms.SecUser
import com.pms.SystemEntity
import com.pms.SystemEntityType
import grails.converters.JSON
import grails.plugin.springsecurity.SpringSecurityService
import groovy.sql.GroovyRowResult
import service.MeetingLogService
import service.SecUserService

class MeetingLogController extends BaseController {

    private static final String MEETING_TYPE = "Meeting Type"

    static allowedMethods = [
            show: "POST", create: "POST", update: "POST",delete: "POST", list: "POST"
    ]

    BaseService baseService
    SpringSecurityService springSecurityService
    SecUserService secUserService
    MeetingLogService meetingLogService
    CreateMeetingLogActionService createMeetingLogActionService
    UpdateMeetingLogActionService updateMeetingLogActionService
    DeleteMeetingLogActionService deleteMeetingLogActionService
    ListMeetingLogActionService listMeetingLogActionService



    def show() {
        def loggedUser = springSecurityService.principal
        SecUser user = SecUser.read(loggedUser.id)
        PmServiceSector service = PmServiceSector.read(user.serviceId)
        boolean isAdmin = baseService.isUserSystemAdmin(user.id)
        List<GroovyRowResult> lstEmployee = secUserService.currentDepartmentEmpList(user)
        lstEmployee.remove(0)
        SystemEntityType type = SystemEntityType.findByName(MEETING_TYPE)
        SystemEntity meetingType = SystemEntity.findByNameAndTypeId(params.type.toString(),type.id)
        render(view: "/meetingLog/show", model: [isAdmin:isAdmin,
                                                 serviceId:user.serviceId,
                                                 categoryId:service.categoryId,
                                                 lstEmployee: lstEmployee as JSON,
                                                 meetingTypeId:meetingType.id,
                                                 meetingType: meetingType.name])
    }
    def create() {
        renderOutput(createMeetingLogActionService, params)
    }
    def update() {
        renderOutput(updateMeetingLogActionService, params)

    }
    def delete() {
        renderOutput(deleteMeetingLogActionService, params)

    }
    def list() {
        renderOutput(listMeetingLogActionService, params)
    }
    def detailsLog(){
        long id = Long.parseLong(params.id.toString())
        long meetingTypeId = Long.parseLong(params.meetingTypeId.toString())
        SystemEntity meetingType = SystemEntity.read(meetingTypeId)
        List<GroovyRowResult> resultSet = meetingLogService.meetingDetails(id)
        render(view: "/reports/meetingLog/showMeetingDetails", model: [resultSet:resultSet[0],meetingType: meetingType.name])
    }
    def showQuarterAnnual() {
        def loggedUser = springSecurityService.principal
        SecUser user = SecUser.read(loggedUser.id)
        long serviceId= baseService.serviceIdByRole()
        boolean isAdmin = baseService.isUserSystemAdmin(user.id)
        SystemEntityType type = SystemEntityType.findByName(MEETING_TYPE)
        SystemEntity meetingType = SystemEntity.findByNameAndTypeId(params.type.toString(),type.id)
        render(view: "/meetingLog/quarterAnnual/show", model: [isAdmin:isAdmin,
                                                 serviceId:serviceId,
                                                 meetingTypeId:meetingType.id,
                                                 meetingType: meetingType.name])
    }

}
