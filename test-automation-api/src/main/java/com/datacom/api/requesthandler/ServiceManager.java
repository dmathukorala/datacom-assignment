package com.datacom.api.requesthandler;


import com.datacom.api.dto.request.RequestParamsDTO;
import com.datacom.api.dto.response.base.BaseResponseDTO;

public class ServiceManager {

    public BaseResponseDTO sendRequest(String requestIdentifier, RequestParamsDTO requestParams) {
        RequestCoordinator requestCoordinator = new RequestCoordinator();
        return requestCoordinator.sendRequest(requestIdentifier, requestParams);
    }

}
