package service

import grails.transaction.Transactional
import groovy.sql.GroovyRowResult
import pms.BaseService

@Transactional
class PmGoalsService extends BaseService{

    public List<GroovyRowResult> lstGoalsForDropDown(long serviceId, int year) {
        String queryForList = """
            SELECT g.id AS id, CONCAT(g.sequence,'. ',REPLACE(g.goal, '\\'', '')) AS name
                FROM pm_goals g
                WHERE g.service_id = ${serviceId}
                AND g.year = ${year}
                ORDER BY g.sequence
        """
        List<GroovyRowResult> lstGoals = executeSelectSql(queryForList)
        return lstGoals
    }
}
