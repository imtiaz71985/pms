package service

import com.pms.PmServiceSector
import grails.transaction.Transactional
import groovy.sql.GroovyRowResult
import pms.BaseService

@Transactional
class PmServiceSectorService extends BaseService{

    def activeList() {
        String queryForList = """
                SELECT s.id AS id, (CASE WHEN s.short_name IS NOT NULL THEN CONCAT(s.name,' (',s.short_name,')')
                ELSE s.name END) AS name
                        FROM pm_service_sector s
                        WHERE s.is_displayble = TRUE
                        ORDER BY s.name ASC
        """
        List<GroovyRowResult> lst = executeSelectSql(queryForList)
        lst = listForKendoDropdown(lst, null, null)
        return lst
    }
    def categoryWiseServiceList(long categoryId) {
        List<PmServiceSector> lst = PmServiceSector.findAllByCategoryId(categoryId, [sort: 'sequence', order: 'asc'])
        lst = listForKendoDropdown(lst, null, null)
        return lst
    }
    public List<GroovyRowResult> listServices(boolean isInSP,boolean considerAll) {
        String spStr = EMPTY_SPACE
        String considerStr = EMPTY_SPACE
        String param = currentUserDepartmentListStr()
        if(isInSP) spStr = " AND is_in_sp IN (${isInSP}) "
        if(!considerAll) considerStr = " AND id IN (${param}) "
        String queryForList = """
            SELECT id, CONCAT(name,' (',short_name,')') AS name
                FROM mis.service
            WHERE is_displayble = TRUE
            ${considerStr} ${spStr}
            ORDER BY name ASC
        """
        List<GroovyRowResult> lstServices = groovySql_mis.rows(queryForList)
        lstServices = listForKendoDropdown(lstServices, null, null)
        return lstServices
    }
}
