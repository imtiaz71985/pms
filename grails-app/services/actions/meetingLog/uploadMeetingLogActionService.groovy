package actions.meetingLog

import com.model.ListMeetingLogActionServiceModel
import com.pms.MeetingLog
import grails.plugin.springsecurity.SpringSecurityService
import grails.transaction.Transactional
import org.apache.log4j.Logger
import pms.ActionServiceIntf
import pms.BaseService
import pms.utility.DateUtility
import service.MeetingLogService

@Transactional
class UploadMeetingLogActionService extends BaseService implements ActionServiceIntf {

    private static final String UPDATE_SUCCESS_MESSAGE = "Log has been uploaded successfully"
    private static final String MEETING_LOG = "meetingLog"
    SpringSecurityService springSecurityService

    private Logger log = Logger.getLogger(getClass())

    MeetingLogService meetingLogService

    public Map executePreCondition(Map params) {
        try {
            if (!params.id) {
                return super.setError(params, INVALID_INPUT_MSG)
            }
            long id = Long.parseLong(params.id.toString())


            MeetingLog oldObject = MeetingLog.read(id)
            oldObject.uploadBy = springSecurityService?.principal?.id
            oldObject.uploadDate = DateUtility.getSqlDate(new Date())
            oldObject.isUploaded = true
            params.put(MEETING_LOG, oldObject)
            return params
        } catch (Exception ex) {
            log.error(ex.getMessage())
            throw new RuntimeException(ex)
        }
    }

    @Transactional
    public Map execute(Map result) {
        try {
            MeetingLog meetingLog = (MeetingLog) result.get(MEETING_LOG)
            meetingLog.save()
            return result
        } catch (Exception ex) {
            log.error(ex.getMessage())
            throw new RuntimeException(ex)
        }
    }
    /**
     *
     * @param result - map received from execute method
     * @return - map
     */
    public Map executePostCondition(Map result) {
        return result
    }
    /**
     *
     * @param result - map received from execute method
     * @return - map with success message
     */
    public Map buildSuccessResultForUI(Map result) {
        MeetingLog meetingLog = (MeetingLog) result.get(MEETING_LOG)
        ListMeetingLogActionServiceModel model = ListMeetingLogActionServiceModel.read(meetingLog.id)
        result.put(MEETING_LOG, model)
        return super.setSuccess(result, UPDATE_SUCCESS_MESSAGE)
    }

    /**
     *
     * @param result - map received from previous method
     * @return - map
     */
    public Map buildFailureResultForUI(Map params) {
        return params
    }

    private static MeetingLog buildObject(MeetingLog oldObject) {
        return oldObject
    }

}
