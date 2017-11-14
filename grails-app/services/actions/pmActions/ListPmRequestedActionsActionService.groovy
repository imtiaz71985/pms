package actions.pmActions

import com.model.ListPmActionsActionServiceModel
import com.pms.PmSpLog
import com.pms.SecUser
import com.pms.SpTimeSchedule
import grails.transaction.Transactional
import groovy.sql.GroovyRowResult
import org.apache.log4j.Logger
import pms.ActionServiceIntf
import pms.BaseService
import pms.utility.DateUtility

import java.text.SimpleDateFormat

@Transactional
class ListPmRequestedActionsActionService extends BaseService implements ActionServiceIntf {

    private Logger log = Logger.getLogger(getClass())

    public Map executePreCondition(Map params) {
        return params
    }

    @Transactional(readOnly = true)
    public Map execute(Map result) {
        try {
            int year = Integer.parseInt(result.year.toString())

            Long serviceId=Long.parseLong(result.serviceId.toString())

            List<GroovyRowResult> lstAction = buildActionsList(serviceId, year)
            result.put(LIST, lstAction)

            result.put(COUNT, lstAction.size())
            return result
        } catch (Exception e) {
            log.error(e.getMessage())
            throw new RuntimeException(e)
        }
    }

    /**
     * There is no postCondition, so return the same map as received
     *
     * @param result - resulting map from execute
     * @return - same map of input-parameter containing isError(true/false)
     */
    public Map executePostCondition(Map result) {
        return result
    }

    /**
     * Since there is no success message return the same map
     * @param result -map from execute/executePost method
     * @return - same map of input-parameter containing isError(true/false)
     */
    public Map buildSuccessResultForUI(Map result) {
        return result
    }

    /**
     * The input-parameter Map must have "isError:true" with corresponding message
     * @param result -map returned from previous methods
     * @return - same map of input-parameter containing isError(true/false)
     */
    public Map buildFailureResultForUI(Map result) {
        return result
    }
    private List<GroovyRowResult> buildActionsList(Long serviceId,int year) {

        String query = """
                SELECT g.goal,a.id,a.sequence,a.start,a.end,COALESCE(GROUP_CONCAT(CONCAT('<strike>',DATE_FORMAT(aeh.end,'%M'),'</strike>') SEPARATOR' '),'') extendedEnd
                ,a.actions,a.service_id AS serviceId,a.goal_id AS goalId,a.tmp_seq AS tmpSeq,sc.short_name AS serShortName,
                a.res_person AS resPerson, a.note,a.support_department AS supportDepartment,a.strategy_map_ref AS strategyMapRef,a.source_of_fund AS sourceOfFund,
                (SELECT GROUP_CONCAT(short_name SEPARATOR ', ') FROM pm_service_sector WHERE LOCATE(CONCAT(',',id,',') ,CONCAT(',',a.support_department,','))>0 ) supportDepartmentStr,
                (SELECT GROUP_CONCAT(short_name SEPARATOR ', ') FROM pm_projects WHERE LOCATE(CONCAT(',',id,',') ,CONCAT(',',a.source_of_fund,', '))>0 ) sourceOfFundStr

                FROM pm_actions a
                LEFT JOIN pm_goals g ON g.id = a.goal_id
                LEFT JOIN pm_actions_extend_history aeh ON aeh.actions_id=a.id
                LEFT JOIN pm_service_sector sc ON a.service_id=sc.id
                WHERE YEAR(a.end) = ${year}  AND FIND_IN_SET('${serviceId}',support_department) GROUP BY a.id
                ORDER BY a.service_id,EXTRACT(YEAR FROM a.start) , a.goal_id ,a.tmp_seq

        """
        List<GroovyRowResult> lstValue = executeSelectSql(query)
        return lstValue
    }
}
