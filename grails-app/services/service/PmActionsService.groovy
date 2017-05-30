package service

import com.pms.PmActions
import com.pms.PmActionsIndicatorDetails
import com.pms.SecUser
import grails.transaction.Transactional
import groovy.sql.GroovyRowResult
import pms.BaseService

import java.text.DateFormat
import java.text.SimpleDateFormat

@Transactional
class PmActionsService extends BaseService {

    public List<GroovyRowResult> lstActionsByServiceIdAndYear(long serviceId, String year) {
        String queryForList = """
            SELECT o.id,o.sequence AS name FROM pm_actions o
                WHERE o.service_id = ${serviceId}
                AND YEAR(start)<='${year}' AND YEAR(end) >='${year}'
                ORDER BY o.goal_id,o.tmp_seq
        """
        List<GroovyRowResult> lst = executeSelectSql(queryForList)
        return lst
    }
    public List<GroovyRowResult> lstActionsForDropDown(long goalId) {
        String queryForList = """
            SELECT o.id AS id, CONCAT(o.sequence,'. ',o.actions) AS name
                FROM pm_actions o
                WHERE o.goal_id = ${goalId}
                ORDER BY o.tmp_seq
        """
        List<GroovyRowResult> lst = executeSelectSql(queryForList)
        return lst
    }
    public boolean taskDateWithinActionsDate(Date start,Date end,long actionsId) {
        String queryForList = """
            SELECT COUNT(id) as c FROM pm_actions
            WHERE START<='${start}' AND END >='${end}' AND id=${actionsId}
        """
        List<GroovyRowResult> lst = executeSelectSql(queryForList)
        int count =(int) lst[0].c
        boolean isWithin=false
        if(count>0){
            isWithin=true
        }
        return isWithin
    }
    public List<GroovyRowResult> lstDepartmentSpStatus(String dateStr) {
        int year = Integer.parseInt(dateStr)

        String query = """
            SELECT ss.name service,l.id AS log_id,l.dead_line,
                l.submission_date,l.is_editable,l.is_submitted,l.year
            FROM pm_service_sector ss
            LEFT JOIN pm_sp_log l ON l.service_id = ss.id AND l.year = ${year}
            WHERE ss.is_in_sp = TRUE
            ORDER BY ss.name,l.year;
        """
        List<GroovyRowResult> lst = executeSelectSql(query)
        return lst
    }
    public List<GroovyRowResult> lstDepartmentMcrsStatus(String yearStr) {
        int year = Integer.parseInt(yearStr)
        String query = """
                SELECT tmp.short_name service,
                MAX(tmp.JanuaryID) JanuaryID     ,MAX(tmp.January) January     ,MAX(tmp.JanuaryDsb) JanuaryDsb     ,MAX(tmp.JanuaryD) JanuaryD ,
                MAX(tmp.FebruaryID) FebruaryID   ,MAX(tmp.February) February   ,MAX(tmp.FebruaryDsb) FebruaryDsb   ,MAX(tmp.FebruaryD) FebruaryD ,
                MAX(tmp.MarchID) MarchID         ,MAX(tmp.March) March         ,MAX(tmp.MarchDsb) MarchDsb         ,MAX(tmp.MarchD) MarchD ,
                MAX(tmp.AprilID) AprilID         ,MAX(tmp.April) April         ,MAX(tmp.AprilDsb) AprilDsb         ,MAX(tmp.AprilD) AprilD ,
                MAX(tmp.MayID) MayID             ,MAX(tmp.May) May             ,MAX(tmp.MayDsb) MayDsb             ,MAX(tmp.MayD) MayD ,
                MAX(tmp.JuneID) JuneID           ,MAX(tmp.June) June           ,MAX(tmp.JuneDsb) JuneDsb           ,MAX(tmp.JuneD) JuneD ,
                MAX(tmp.JulyID) JulyID           ,MAX(tmp.July) July           ,MAX(tmp.JulyDsb) JulyDsb           ,MAX(tmp.JulyD) JulyD ,
                MAX(tmp.AugustID) AugustID       ,MAX(tmp.August) August       ,MAX(tmp.AugustDsb) AugustDsb       ,MAX(tmp.AugustD) AugustD ,
                MAX(tmp.SeptemberID) SeptemberID ,MAX(tmp.September) September ,MAX(tmp.SeptemberDsb) SeptemberDsb ,MAX(tmp.SeptemberD) SeptemberD ,
                MAX(tmp.OctoberID) OctoberID     ,MAX(tmp.October) October     ,MAX(tmp.OctoberDsb) OctoberDsb     ,MAX(tmp.OctoberD) OctoberD,
                MAX(tmp.NovemberID) NovemberID   ,MAX(tmp.November) November   ,MAX(tmp.NovemberDsb) NovemberDsb   ,MAX(tmp.NovemberD) NovemberD  ,
                MAX(tmp.DecemberID) DecemberID   ,MAX(tmp.December) December   ,MAX(tmp.DecemberDsb) DecemberDsb   ,MAX(tmp.DecemberD) DecemberD
                FROM (SELECT ss.short_name,
                CASE WHEN l.month_str='January' THEN l.id ELSE NULL END JanuaryID,
                CASE WHEN l.month_str='January' THEN l.submission_date ELSE NULL END January,
                CASE WHEN l.month_str='January' THEN l.submission_date_db ELSE NULL END JanuaryDsb,
                CASE WHEN l.month_str='January' THEN l.dead_line ELSE NULL END JanuaryD,

                CASE WHEN l.month_str='February' THEN l.id ELSE NULL END FebruaryID,
                CASE WHEN l.month_str='February' THEN l.submission_date ELSE NULL END February,
                CASE WHEN l.month_str='February' THEN l.submission_date_db ELSE NULL END FebruaryDsb,
                CASE WHEN l.month_str='February' THEN l.dead_line ELSE NULL END FebruaryD,

                CASE WHEN l.month_str='March' THEN l.id ELSE NULL END MarchID,
                CASE WHEN l.month_str='March' THEN l.submission_date ELSE NULL END March,
                CASE WHEN l.month_str='March' THEN l.submission_date_db ELSE NULL END MarchDsb,
                CASE WHEN l.month_str='March' THEN l.dead_line ELSE NULL END MarchD,

                CASE WHEN l.month_str='April' THEN l.id ELSE NULL END AprilID,
                CASE WHEN l.month_str='April' THEN l.submission_date ELSE NULL END April,
                CASE WHEN l.month_str='April' THEN l.submission_date_db ELSE NULL END AprilDsb,
                CASE WHEN l.month_str='April' THEN l.dead_line ELSE NULL END AprilD,

                CASE WHEN l.month_str='May' THEN l.id ELSE NULL END MayID,
                CASE WHEN l.month_str='May' THEN l.submission_date ELSE NULL END May,
                CASE WHEN l.month_str='May' THEN l.submission_date_db ELSE NULL END MayDsb,
                CASE WHEN l.month_str='May' THEN l.dead_line ELSE NULL END MayD,

                CASE WHEN l.month_str='June' THEN l.id ELSE NULL END JuneID,
                CASE WHEN l.month_str='June' THEN l.submission_date ELSE NULL END June,
                CASE WHEN l.month_str='June' THEN l.submission_date_db ELSE NULL END JuneDsb,
                CASE WHEN l.month_str='June' THEN l.dead_line ELSE NULL END JuneD,

                CASE WHEN l.month_str='July' THEN l.id ELSE NULL END JulyID,
                CASE WHEN l.month_str='July' THEN l.submission_date ELSE NULL END July,
                CASE WHEN l.month_str='July' THEN l.submission_date_db ELSE NULL END JulyDsb,
                CASE WHEN l.month_str='July' THEN l.dead_line ELSE NULL END JulyD,

                CASE WHEN l.month_str='August' THEN l.id ELSE NULL END AugustID,
                CASE WHEN l.month_str='August' THEN l.submission_date ELSE NULL END August,
                CASE WHEN l.month_str='August' THEN l.submission_date_db ELSE NULL END AugustDsb,
                CASE WHEN l.month_str='August' THEN l.dead_line ELSE NULL END AugustD,

                CASE WHEN l.month_str='September' THEN l.id ELSE NULL END SeptemberID,
                CASE WHEN l.month_str='September' THEN l.submission_date ELSE NULL END September,
                CASE WHEN l.month_str='September' THEN l.submission_date_db ELSE NULL END SeptemberDsb,
                CASE WHEN l.month_str='September' THEN l.dead_line ELSE NULL END SeptemberD,

                CASE WHEN l.month_str='October' THEN l.id ELSE NULL END OctoberID,
                CASE WHEN l.month_str='October' THEN l.submission_date ELSE NULL END October,
                CASE WHEN l.month_str='October' THEN l.submission_date_db ELSE NULL END OctoberDsb,
                CASE WHEN l.month_str='October' THEN l.dead_line ELSE NULL END OctoberD,

                CASE WHEN l.month_str='November' THEN l.id ELSE NULL END NovemberID,
                CASE WHEN l.month_str='November' THEN l.submission_date ELSE NULL END November,
                CASE WHEN l.month_str='November' THEN l.submission_date_db ELSE NULL END NovemberDsb,
                CASE WHEN l.month_str='November' THEN l.dead_line ELSE NULL END NovemberD,

                CASE WHEN l.month_str='December' THEN l.id ELSE NULL END DecemberID,
                CASE WHEN l.month_str='December' THEN l.submission_date ELSE NULL END December,
                CASE WHEN l.month_str='December' THEN l.submission_date_db ELSE NULL END DecemberDsb,
                CASE WHEN l.month_str='December' THEN l.dead_line ELSE NULL END DecemberD
                FROM pm_service_sector ss
                LEFT JOIN pm_mcrs_log l ON l.service_id = ss.id AND l.year = ${year}
                WHERE ss.is_in_sp = TRUE
                ORDER BY ss.name,l.month) tmp GROUP BY tmp.short_name;
        """
        List<GroovyRowResult> lst = executeSelectSql(query)
        return lst
    }
    public List<GroovyRowResult> lstServiceWiseAcvStatus(String monthStr) {
        DateFormat originalFormat = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
        Date date = originalFormat.parse(monthStr);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH)+1
        int year = c.get(Calendar.YEAR)
        SecUser user = currentUserObject();

        String query = """
                    SELECT ROUND(SUM(a_pert)/COUNT(cat_axe)) act_val, "Achieved" act_name,"#069302" act_color
            FROM (
                SELECT t.cat_axe,IF(ROUND(SUM(t.avgr)/COUNT(t.actions_id))>100,100,ROUND(SUM(t.avgr)/COUNT(t.actions_id))) a_pert
                FROM (
                    SELECT t.service_id,t.goal_id,t.cat_axe,t.actions_id,
                    SUM(ROUND((t.achievement/t.target)*100,2))/COUNT(t.indicator_id) avgr
                    FROM (
                        SELECT sc.id service_id,g.id AS goal_id,sc.name service,sc.short_name,a.id actions_id,ai.id indicator_id,
                        CONCAT('Goal ',g.sequence) cat_axe,aid.target,COALESCE(aid.achievement,0) achievement
                        FROM pm_goals g
                        LEFT JOIN pm_service_sector sc ON sc.id = g.service_id
                        LEFT JOIN pm_actions a ON a.goal_id = g.id
                        LEFT JOIN pm_actions_indicator ai ON a.id = ai.actions_id
                        LEFT JOIN pm_actions_indicator_details aid ON ai.id=aid.indicator_id
                        JOIN custom_month cm ON cm.name=aid.month_name,
                        (SELECT @curmon := ${month}) r
                        WHERE a.year = ${year} AND cm.sl_index=@curmon AND aid.target > 0 AND a.service_id = ${user.serviceId}
                        ORDER BY sc.sequence,g.id,a.id,ai.id,aid.id
                    ) t GROUP BY t.goal_id ,t.actions_id
                ) t GROUP BY t.goal_id
            ) tmp

            UNION ALL

            SELECT 100-ROUND(SUM(a_pert)/COUNT(cat_axe)) act_val, "Remaining" act_name,"#ff8a00" act_color
            FROM (
                SELECT t.cat_axe,IF(ROUND(SUM(t.avgr)/COUNT(t.actions_id))>100,100,ROUND(SUM(t.avgr)/COUNT(t.actions_id))) a_pert
                FROM (
                    SELECT t.service_id,t.goal_id,t.cat_axe,t.actions_id,
                    SUM(ROUND((t.achievement/t.target)*100,2))/COUNT(t.indicator_id) avgr
                    FROM (
                        SELECT sc.id service_id,g.id AS goal_id,sc.name service,sc.short_name,a.id actions_id,ai.id indicator_id,
                        CONCAT('Goal ',g.sequence) cat_axe,aid.target,COALESCE(aid.achievement,0) achievement
                        FROM pm_goals g
                        LEFT JOIN pm_service_sector sc ON sc.id = g.service_id
                        LEFT JOIN pm_actions a ON a.goal_id = g.id
                        LEFT JOIN pm_actions_indicator ai ON a.id = ai.actions_id
                        LEFT JOIN pm_actions_indicator_details aid ON ai.id=aid.indicator_id
                        JOIN custom_month cm ON cm.name=aid.month_name,
                        (SELECT @curmon := ${month}) r
                        WHERE a.year = ${year} AND cm.sl_index=@curmon AND aid.target > 0 AND a.service_id = ${user.serviceId}
                        ORDER BY sc.sequence,g.id,a.id,ai.id,aid.id
                    ) t GROUP BY t.goal_id ,t.actions_id
                ) t GROUP BY t.goal_id
            ) tmp2;
        """
        println(query)
        List<GroovyRowResult> lst = groovySql.rows(query)
        return lst
    }
    public List<GroovyRowResult> lstGoalWiseActionStatus(String monthStr) {
        DateFormat originalFormat = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
        Date date = originalFormat.parse(monthStr);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH)+1
        int year = c.get(Calendar.YEAR)
        SecUser user = currentUserObject();

        String query = """
                SELECT t.cat_axe,t.goal,COUNT(t.actions_id) ac_count,'#ff8a00' t_color, '#069302' a_color,
                IF(ROUND(SUM(t.avgr)/COUNT(t.actions_id))>100,100,ROUND(SUM(t.avgr)/COUNT(t.actions_id))) a_pert,
                ROUND(IF(ROUND(SUM(t.avgr)/COUNT(t.actions_id))>100,100,ROUND(SUM(t.avgr)/COUNT(t.actions_id)))*COUNT(t.actions_id)/100,2) a_col,
                COUNT(t.actions_id)-ROUND(IF(ROUND(SUM(t.avgr)/COUNT(t.actions_id))>100,100,ROUND(SUM(t.avgr)/COUNT(t.actions_id)))*COUNT(t.actions_id)/100,2) t_col
                FROM (
                    SELECT t.goal_id,t.goal,t.cat_axe,t.actions_id,
                    SUM(ROUND((t.achievement/t.target)*100,2))/COUNT(t.indicator_id) avgr
                    FROM (
                        SELECT g.id AS goal_id,g.goal,a.id actions_id,ai.id indicator_id,
                        CONCAT('Goal ',g.sequence) cat_axe,aid.target,COALESCE(aid.achievement,0) achievement
                        FROM pm_goals g
                        LEFT JOIN pm_service_sector sc ON sc.id = g.service_id
                        LEFT JOIN pm_actions a ON a.goal_id = g.id
                        LEFT JOIN pm_actions_indicator ai ON a.id = ai.actions_id
                        LEFT JOIN pm_actions_indicator_details aid ON ai.id=aid.indicator_id
                        JOIN custom_month cm ON cm.name=aid.month_name,
                        (SELECT @curmon := ${month}) r
                        WHERE g.service_id = ${user.serviceId} AND a.year = ${year} AND cm.sl_index=@curmon AND aid.target > 0
                        ORDER BY sc.sequence,g.id,a.id,ai.id,aid.id
                    ) t GROUP BY t.goal_id ,t.actions_id
                ) t GROUP BY t.goal_id
        """
        println(query)
        List<GroovyRowResult> lst = groovySql.rows(query)
        return lst
    }
    public List<GroovyRowResult> lstServiceWiseActionStatus(String monthStr) {
        DateFormat originalFormat = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
        Date date = originalFormat.parse(monthStr);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH)+1
        int year = c.get(Calendar.YEAR)

        String query = """
            SELECT tmp.service_id,tmp.service,tmp.short_name,
            ROUND(SUM(tmp.avgr)/COUNT(tmp.goal_id)) a_pert,'#069302' a_color,
            100-ROUND(SUM(tmp.avgr)/COUNT(tmp.goal_id)) r_pert,'#ff8a00' r_color
            FROM (
                SELECT t.service_id,t.goal_id,t.service,t.short_name,t.cat_axe,
                IF(ROUND(SUM(t.avgr)/COUNT(t.actions_id))>100,100,ROUND(SUM(t.avgr)/COUNT(t.actions_id))) avgr
                FROM (
                    SELECT t.service_id,t.goal_id,t.service,t.short_name,t.cat_axe,t.actions_id,
                    SUM(ROUND((t.achievement/t.target)*100,2))/COUNT(t.indicator_id) avgr
                    FROM (
                        SELECT sc.id service_id,g.id AS goal_id,sc.name service,sc.short_name,a.id actions_id,ai.id indicator_id,
                        CONCAT('Goal ',g.sequence) cat_axe,aid.target,COALESCE(aid.achievement,0) achievement
                        FROM pm_goals g
                        LEFT JOIN pm_service_sector sc ON sc.id = g.service_id
                        LEFT JOIN pm_actions a ON a.goal_id = g.id
                        LEFT JOIN pm_actions_indicator ai ON a.id = ai.actions_id
                        LEFT JOIN pm_actions_indicator_details aid ON ai.id=aid.indicator_id
                        JOIN custom_month cm ON cm.name=aid.month_name,
                        (SELECT @curmon := ${month}) r
                        WHERE a.year = ${year} AND cm.sl_index=@curmon AND aid.target > 0
                        ORDER BY sc.sequence,g.id,a.id,ai.id,aid.id
                    ) t GROUP BY t.goal_id ,t.actions_id
                ) t GROUP BY t.goal_id
            ) tmp GROUP BY service_id;
        """
        println(query)
        List<GroovyRowResult> lst = groovySql.rows(query)
        return lst
    }
    public List<GroovyRowResult> findAllDetailsByActionsIdAndIndicatorId(long actionsId, long indicatorId) {
        String query = """
            SELECT aid.id,aid.version,aid.actions_id,aid.create_by,aid.create_date,aid.indicator_id,aid.month_name,
                COALESCE(aid.target,0) target,COALESCE(aid.achievement,0) achievement,aid.remarks,ai.indicator_type
                FROM pm_actions_indicator_details aid
                LEFT JOIN pm_actions_indicator ai ON ai.id=aid.indicator_id
                WHERE aid.actions_id = ${actionsId} AND aid.indicator_id = ${indicatorId}
        """
        List<GroovyRowResult> lst = executeSelectSql(query)
        return lst
    }
    public void deleteInvalidIndicatorDetails(long indicatorId, String start, String end) {
        if (start == 'February') {
            PmActionsIndicatorDetails jan = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'January')
            jan?.delete()
        } else if (start == 'March') {
            PmActionsIndicatorDetails jan = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'January')
            jan?.delete()
            PmActionsIndicatorDetails feb = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'February')
            feb?.delete()
        } else if (start == 'April') {
            PmActionsIndicatorDetails jan = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'January')
            jan?.delete()
            PmActionsIndicatorDetails feb = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'February')
            feb?.delete()
            PmActionsIndicatorDetails mar = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'March')
            mar?.delete()
        } else if (start == 'May') {
            PmActionsIndicatorDetails jan = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'January')
            jan?.delete()
            PmActionsIndicatorDetails feb = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'February')
            feb?.delete()
            PmActionsIndicatorDetails mar = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'March')
            mar?.delete()
            PmActionsIndicatorDetails apr = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'April')
            apr?.delete()
        } else if (start == 'June') {
            PmActionsIndicatorDetails jan = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'January')
            jan?.delete()
            PmActionsIndicatorDetails feb = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'February')
            feb?.delete()
            PmActionsIndicatorDetails mar = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'March')
            mar?.delete()
            PmActionsIndicatorDetails apr = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'April')
            apr?.delete()
            PmActionsIndicatorDetails may = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'May')
            may?.delete()
        } else if (start == 'July') {
            PmActionsIndicatorDetails jan = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'January')
            jan?.delete()
            PmActionsIndicatorDetails feb = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'February')
            feb?.delete()
            PmActionsIndicatorDetails mar = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'March')
            mar?.delete()
            PmActionsIndicatorDetails apr = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'April')
            apr?.delete()
            PmActionsIndicatorDetails may = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'May')
            may?.delete()
            PmActionsIndicatorDetails june = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'June')
            june?.delete()
        } else if (start == 'August') {
            PmActionsIndicatorDetails jan = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'January')
            jan?.delete()
            PmActionsIndicatorDetails feb = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'February')
            feb?.delete()
            PmActionsIndicatorDetails mar = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'March')
            mar?.delete()
            PmActionsIndicatorDetails apr = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'April')
            apr?.delete()
            PmActionsIndicatorDetails may = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'May')
            may?.delete()
            PmActionsIndicatorDetails june = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'June')
            june?.delete()
            PmActionsIndicatorDetails july = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'July')
            july?.delete()
        } else if (start == 'September') {
            PmActionsIndicatorDetails jan = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'January')
            jan?.delete()
            PmActionsIndicatorDetails feb = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'February')
            feb?.delete()
            PmActionsIndicatorDetails mar = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'March')
            mar?.delete()
            PmActionsIndicatorDetails apr = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'April')
            apr?.delete()
            PmActionsIndicatorDetails may = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'May')
            may?.delete()
            PmActionsIndicatorDetails june = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'June')
            june?.delete()
            PmActionsIndicatorDetails july = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'July')
            july?.delete()
            PmActionsIndicatorDetails aug = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'August')
            aug?.delete()
        } else if (start == 'October') {
            PmActionsIndicatorDetails jan = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'January')
            jan?.delete()
            PmActionsIndicatorDetails feb = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'February')
            feb?.delete()
            PmActionsIndicatorDetails mar = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'March')
            mar?.delete()
            PmActionsIndicatorDetails apr = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'April')
            apr?.delete()
            PmActionsIndicatorDetails may = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'May')
            may?.delete()
            PmActionsIndicatorDetails june = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'June')
            june?.delete()
            PmActionsIndicatorDetails july = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'July')
            july?.delete()
            PmActionsIndicatorDetails aug = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'August')
            aug?.delete()
            PmActionsIndicatorDetails sep = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'September')
            sep?.delete()
        } else if (start == 'November') {
            PmActionsIndicatorDetails jan = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'January')
            jan?.delete()
            PmActionsIndicatorDetails feb = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'February')
            feb?.delete()
            PmActionsIndicatorDetails mar = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'March')
            mar?.delete()
            PmActionsIndicatorDetails apr = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'April')
            apr?.delete()
            PmActionsIndicatorDetails may = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'May')
            may?.delete()
            PmActionsIndicatorDetails june = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'June')
            june?.delete()
            PmActionsIndicatorDetails july = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'July')
            july?.delete()
            PmActionsIndicatorDetails aug = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'August')
            aug?.delete()
            PmActionsIndicatorDetails sep = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'September')
            sep?.delete()
            PmActionsIndicatorDetails oct = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'October')
            oct?.delete()
        } else if (start == 'December') {
            PmActionsIndicatorDetails jan = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'January')
            jan?.delete()
            PmActionsIndicatorDetails feb = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'February')
            feb?.delete()
            PmActionsIndicatorDetails mar = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'March')
            mar?.delete()
            PmActionsIndicatorDetails apr = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'April')
            apr?.delete()
            PmActionsIndicatorDetails may = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'May')
            may?.delete()
            PmActionsIndicatorDetails june = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'June')
            june?.delete()
            PmActionsIndicatorDetails july = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'July')
            july?.delete()
            PmActionsIndicatorDetails aug = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'August')
            aug?.delete()
            PmActionsIndicatorDetails sep = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'September')
            sep?.delete()
            PmActionsIndicatorDetails oct = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'October')
            oct?.delete()
            PmActionsIndicatorDetails nov = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'November')
            nov?.delete()
        }

        if (end == 'January') {
            PmActionsIndicatorDetails feb = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'February')
            feb?.delete()
            PmActionsIndicatorDetails mar = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'March')
            mar?.delete()
            PmActionsIndicatorDetails apr = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'April')
            apr?.delete()
            PmActionsIndicatorDetails may = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'May')
            may?.delete()
            PmActionsIndicatorDetails june = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'June')
            june?.delete()
            PmActionsIndicatorDetails july = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'July')
            july?.delete()
            PmActionsIndicatorDetails aug = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'August')
            aug?.delete()
            PmActionsIndicatorDetails sep = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'September')
            sep?.delete()
            PmActionsIndicatorDetails oct = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'October')
            oct?.delete()
            PmActionsIndicatorDetails nov = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'November')
            nov?.delete()
            PmActionsIndicatorDetails dec = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'December')
            dec?.delete()
        }else if (end == 'February') {
            PmActionsIndicatorDetails mar = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'March')
            mar?.delete()
            PmActionsIndicatorDetails apr = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'April')
            apr?.delete()
            PmActionsIndicatorDetails may = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'May')
            may?.delete()
            PmActionsIndicatorDetails june = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'June')
            june?.delete()
            PmActionsIndicatorDetails july = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'July')
            july?.delete()
            PmActionsIndicatorDetails aug = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'August')
            aug?.delete()
            PmActionsIndicatorDetails sep = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'September')
            sep?.delete()
            PmActionsIndicatorDetails oct = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'October')
            oct?.delete()
            PmActionsIndicatorDetails nov = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'November')
            nov?.delete()
            PmActionsIndicatorDetails dec = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'December')
            dec?.delete()
        } else if (end == 'March') {
            PmActionsIndicatorDetails apr = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'April')
            apr?.delete()
            PmActionsIndicatorDetails may = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'May')
            may?.delete()
            PmActionsIndicatorDetails june = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'June')
            june?.delete()
            PmActionsIndicatorDetails july = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'July')
            july?.delete()
            PmActionsIndicatorDetails aug = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'August')
            aug?.delete()
            PmActionsIndicatorDetails sep = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'September')
            sep?.delete()
            PmActionsIndicatorDetails oct = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'October')
            oct?.delete()
            PmActionsIndicatorDetails nov = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'November')
            nov?.delete()
            PmActionsIndicatorDetails dec = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'December')
            dec?.delete()
        } else if (end == 'April') {
            PmActionsIndicatorDetails may = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'May')
            may?.delete()
            PmActionsIndicatorDetails june = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'June')
            june?.delete()
            PmActionsIndicatorDetails july = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'July')
            july?.delete()
            PmActionsIndicatorDetails aug = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'August')
            aug?.delete()
            PmActionsIndicatorDetails sep = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'September')
            sep?.delete()
            PmActionsIndicatorDetails oct = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'October')
            oct?.delete()
            PmActionsIndicatorDetails nov = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'November')
            nov?.delete()
            PmActionsIndicatorDetails dec = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'December')
            dec?.delete()
        } else if (end == 'May') {
            PmActionsIndicatorDetails june = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'June')
            june?.delete()
            PmActionsIndicatorDetails july = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'July')
            july?.delete()
            PmActionsIndicatorDetails aug = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'August')
            aug?.delete()
            PmActionsIndicatorDetails sep = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'September')
            sep?.delete()
            PmActionsIndicatorDetails oct = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'October')
            oct?.delete()
            PmActionsIndicatorDetails nov = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'November')
            nov?.delete()
            PmActionsIndicatorDetails dec = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'December')
            dec?.delete()
        } else if (end == 'June') {
            PmActionsIndicatorDetails july = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'July')
            july?.delete()
            PmActionsIndicatorDetails aug = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'August')
            aug?.delete()
            PmActionsIndicatorDetails sep = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'September')
            sep?.delete()
            PmActionsIndicatorDetails oct = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'October')
            oct?.delete()
            PmActionsIndicatorDetails nov = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'November')
            nov?.delete()
            PmActionsIndicatorDetails dec = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'December')
            dec?.delete()
        } else if (end == 'July') {
            PmActionsIndicatorDetails aug = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'August')
            aug?.delete()
            PmActionsIndicatorDetails sep = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'September')
            sep?.delete()
            PmActionsIndicatorDetails oct = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'October')
            oct?.delete()
            PmActionsIndicatorDetails nov = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'November')
            nov?.delete()
            PmActionsIndicatorDetails dec = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'December')
            dec?.delete()
        } else if (end == 'August') {
            PmActionsIndicatorDetails sep = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'September')
            sep?.delete()
            PmActionsIndicatorDetails oct = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'October')
            oct?.delete()
            PmActionsIndicatorDetails nov = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'November')
            nov?.delete()
            PmActionsIndicatorDetails dec = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'December')
            dec?.delete()
        } else if (end == 'September') {
            PmActionsIndicatorDetails oct = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'October')
            oct?.delete()
            PmActionsIndicatorDetails nov = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'November')
            nov?.delete()
            PmActionsIndicatorDetails dec = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'December')
            dec?.delete()
        } else if (end == 'October') {
            PmActionsIndicatorDetails nov = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'November')
            nov?.delete()
            PmActionsIndicatorDetails dec = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'December')
            dec?.delete()
        } else if (end == 'November') {
            PmActionsIndicatorDetails dec = PmActionsIndicatorDetails.findByIndicatorIdAndMonthName(indicatorId, 'December')
            dec?.delete()
        }
    }
}
