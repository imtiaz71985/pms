package com.pms

class PmSprints {
    long id
    long version
    long serviceId
    long goalId
    long objectiveId
    int sequence
    String actionsId
    String sprints
    String meaIndicator
    String target
    String resPerson
    String strategyMapRef
    String supportDepartment
    String sourceOfFund
    String remarks
    Date startDate
    Date endDate
    int weight
    Date createDate
    long createBy

    static mapping = {
        sprints    sqlType: 'text'
        startDate      sqlType: 'date'
        endDate       sqlType: 'date'
        createDate sqlType: 'date'
        serviceId   index: 'sprints_service_id_idx'
        goalId   index: 'sprints_goal_id_idx'
        objectiveId   index: 'sprints_objective_id_idx'
        actionsId   index: 'sprints_actions_id_idx'
    }

    static constraints = {
        sprints     size: 2..15000
        meaIndicator(nullable: true)
        target(nullable: true)
        resPerson(nullable: true)
        strategyMapRef(nullable: true)
        supportDepartment(nullable: true)
        sourceOfFund(nullable: true)
        remarks(nullable: true)
    }
}
