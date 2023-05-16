package com.datacom.api.constants;

public class RequestIdentifier {

    private RequestIdentifier() {}

    /**
     * RequestIdentifiers - place in alphabetical order
     * Format : <serviceName>_<apiTemplateName>
     * serviceName : Should be same as service directory name in test/resources/ServiceData
     * apiTemplateName : Should be same as api template file name in test/resources/ServiceData/<serviceName>
     */

    public static final String GET_USERS = "datacom_GetUsers";
    public static final String GET_USER_BY_ID = "datacom_GetUserById";
    public static final String CREATE_USER = "datacom_PostUser";

}
