package com.datacom.api.steps;


import com.datacom.api.constants.RequestIdentifier;
import com.datacom.api.dto.request.RequestParamsDTO;
import com.datacom.api.dto.response.base.BaseResponseDTO;
import com.datacom.api.dto.response.users.UsersResponseDTO;
import com.datacom.api.httpmethods.RequestHandler;
import com.datacom.api.requesthandler.ServiceManager;
import com.datacom.api.base.ServiceBase;
import io.qameta.allure.Step;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;


public class UsersSteps extends ServiceBase {

    private ServiceManager serviceManager;
    private Log log = LogFactory.getLog(RequestHandler.class);

    public UsersSteps() {
        serviceManager = new ServiceManager();
    }


    @Step("Get all existing users")
    public UsersResponseDTO[] getUsers(RequestParamsDTO requestParams) {
        UsersResponseDTO[] getUserRespDTO = null;
        try {
            BaseResponseDTO baseResponseDTO = serviceManager.sendRequest(RequestIdentifier.GET_USERS, requestParams);

            getUserRespDTO = (UsersResponseDTO[]) getResponseDTO(baseResponseDTO.getResponse(), UsersResponseDTO[].class);
            getUserRespDTO[0].setResponse(baseResponseDTO.getResponse());

        } catch (Exception e) {
            log.error(e);
            Assert.fail(e.toString());
        }
        return getUserRespDTO;
    }

    @Step("Get existing user by id")
    public UsersResponseDTO getUserById(RequestParamsDTO requestParams) {
        UsersResponseDTO getUserRespDTO = null;
        try {
            BaseResponseDTO baseResponseDTO = serviceManager.sendRequest(RequestIdentifier.GET_USER_BY_ID, requestParams);

            getUserRespDTO = (UsersResponseDTO) getResponseDTO(baseResponseDTO.getResponse(), UsersResponseDTO.class);
            getUserRespDTO.setResponse(baseResponseDTO.getResponse());

        } catch (Exception e) {
            log.error(e);
            Assert.fail(e.toString());
        }
        return getUserRespDTO;
    }

    @Step("Create new user")
    public UsersResponseDTO createUser(RequestParamsDTO requestParams) {
        UsersResponseDTO createUserResponse = null;
        try {
            BaseResponseDTO baseResponseDTO = serviceManager.sendRequest(RequestIdentifier.CREATE_USER, requestParams);

            createUserResponse = (UsersResponseDTO) getResponseDTO(baseResponseDTO.getResponse(), UsersResponseDTO.class);
            createUserResponse.setResponse(baseResponseDTO.getResponse());

        } catch (Exception e) {
            log.error(e);
            Assert.fail(e.toString());
        }
        return createUserResponse;
    }

}
