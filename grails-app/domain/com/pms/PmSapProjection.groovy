package com.pms

class PmSapProjection {

    long id
    long version
    long serviceId
    long goalId
    int creatingYear
    int year
    long target
    long achievement
    Date createDate
    long createBy

    static mapping = {
        serviceId    index: 'sap_projection_service_id_idx'
        goalId    index: 'sap_projection_goal_id_idx'
        creatingYear      index: 'sap_projection_creating_year_idx'
    }

    static constraints = {
    }
}
