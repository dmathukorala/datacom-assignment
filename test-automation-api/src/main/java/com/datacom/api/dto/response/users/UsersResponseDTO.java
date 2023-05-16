package com.datacom.api.dto.response.users;

import com.datacom.api.dto.response.base.BaseResponseDTO;
import lombok.Data;

@Data
public class UsersResponseDTO extends BaseResponseDTO {
    private Integer id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;
}
