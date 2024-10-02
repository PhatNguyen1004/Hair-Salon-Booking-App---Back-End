package com.math.mathcha.dto.response;


import com.math.mathcha.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResUpdateUserDTO {
    private int user_id;

    private String username;

    private String email;

    private String phone_number;

    private String address;

    private String password;

    private Boolean is_deleted;

    private Role role;

}
