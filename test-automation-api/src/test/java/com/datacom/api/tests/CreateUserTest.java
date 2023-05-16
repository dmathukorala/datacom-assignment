package com.datacom.api.tests;

import com.datacom.api.base.TestBase;
import com.datacom.api.dto.request.RequestParamsDTO;
import com.datacom.api.dto.response.users.UsersResponseDTO;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

@Epic("Datacom API Tests")
@Feature("Get Users API")
public class CreateUserTest extends TestBase {

    private RequestParamsDTO requestParams;
    @BeforeEach
    public void beforeEach(){

        requestParams = new RequestParamsDTO();

    }

    @Test
    @DisplayName("TC3: Verify POST Users request")
    @Description("Test Case 3: Verify valid POST User request")
    public void tc3_verify_create_user_request() {
        String name = RandomStringUtils.randomAlphabetic(10);
        String username = RandomStringUtils.randomAlphabetic(10);
        String email = RandomStringUtils.randomAlphabetic(10)+"@gmail.com";

        Map<String, Object> body = new HashMap<>();
        body.put("name", name);
        body.put("username", username);
        body.put("email", email);
        requestParams.setUpdatePayload(body);

        UsersResponseDTO responseDTO = userSteps.createUser(requestParams);
        Assert.assertEquals(201, responseDTO.getStatusCode());
        Assert.assertEquals(name, responseDTO.getName());

        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("id", responseDTO.getId().toString());
        requestParams = new RequestParamsDTO();
        requestParams.setUpdatePathParams(pathParams);
        UsersResponseDTO getUserResponse = userSteps.getUserById(requestParams);
        Assert.assertEquals(200, getUserResponse.getStatusCode());
        Assert.assertEquals(name, getUserResponse.getName());

    }

}
