package com.model

class ListSecUserActionServiceModel {

    public static final String MODEL_NAME = 'list_sec_user_action_service_model'
    public static final String SQL_LIST_SEC_USER_MODEL = """
    DROP TABLE IF EXISTS list_sec_user_action_service_model;
    CREATE OR REPLACE VIEW list_sec_user_action_service_model AS

        SELECT suser.id, suser.version,suser.employee_name full_name,suser.employee_name,
        suser.username,suser.password,suser.enabled, suser.account_expired,
         suser.account_locked, sc.id AS service_id, sc.name AS service
        FROM sec_user suser
        LEFT JOIN mis.service sc ON sc.id = suser.service_id
        ORDER BY suser.employee_name ASC;
    """

    long id                                 // primary key (Auto generated by its own sequence)
    long version                            // entity version in the persistence layer
    String employeeName                     // full name of SecUser
    String fullName                         // full name of SecUser
    String username                         // user name of SecUser
    String password                         // password of SecUser
    long serviceId                          // departmentId
    String service                          // which department the user belongs
    boolean enabled                         // flag to enable SecUser
    boolean accountExpired                  // flag to determine is account expired or not
    boolean accountLocked                   // flag to determine is account locked or not

    static mapping = {
        datasource 'comn'
        cache usage: "read-only"
    }
}
