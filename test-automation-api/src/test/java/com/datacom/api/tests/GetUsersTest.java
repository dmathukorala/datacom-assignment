package com.datacom.api.tests;

import com.datacom.api.dto.request.RequestParamsDTO;
import com.datacom.api.dto.response.users.UsersResponseDTO;
import com.datacom.api.base.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

@Epic("Datacom API Tests")
@Feature("Get Users API")
public class GetUsersTest extends TestBase {

    private RequestParamsDTO requestParams;
    @BeforeEach
    public void beforeEach(){

        requestParams = new RequestParamsDTO();

    }

    @Test
    @DisplayName("TC1: Verify GET Users request")
    @Description("Test Case 1: Verify valid GET Users request")
    public void tc1_verify_get_users_request() {

        UsersResponseDTO[] responseDTO = userSteps.getUsers(requestParams);
        Assert.assertEquals(200, responseDTO[0].getStatusCode());
        Assert.assertEquals(10, responseDTO.length);

    }

    @Test
    @DisplayName("TC2: Verify GET User by id request")
    @Description("Test Case 2: Verify valid GET User by id request")
    public void tc2_verify_get_user_by_id_request() {

        Map<String,String> pathParams = new HashMap<>();
        pathParams.put("id", "8");
        requestParams.setUpdatePathParams(pathParams);
        UsersResponseDTO responseDTO = userSteps.getUserById(requestParams);
        Assert.assertEquals(200, responseDTO.getStatusCode());
        Assert.assertEquals("Nicholas Runolfsdottir V", responseDTO.getName());
    }

}
