package com.model

class ListSecUserSecRoleActionServiceModel implements Serializable {
    public static final String MODEL_NAME = 'list_sec_user_sec_role_action_service_model'

    public static final String SQL_LIST_USER_ROLE_MODEL = """
    DROP TABLE IF EXISTS list_sec_user_sec_role_action_service_model;
    CREATE OR REPLACE VIEW list_sec_user_sec_role_action_service_model AS

    SELECT sec_user_id AS user_id,CONCAT(u.full_name,' (',u.username,')') AS username,
           s.id AS service_id,s.name AS service,sec_role_id AS role_id, role.authority
    FROM sec_user_sec_role ur
         LEFT JOIN sec_user u ON u.id = ur.sec_user_id
         LEFT JOIN pm_service_sector s ON s.id = u.service_id
         LEFT JOIN sec_role role ON role.id = ur.sec_role_id
    WHERE u.enabled = TRUE;
    """

    long userId
    long roleId
    long serviceId
    String username
    String service
    String authority

    static mapping = {
        id composite: ['roleId', 'userId']
        version false
        cache usage: "read-only"
    }
}
