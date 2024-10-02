package com.math.mathcha.dto.response;


import com.math.mathcha.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ResCreateUserDTO {
    private int user_id;

    private String username;

    private String email;

    private String phone_number;

    private String address;

    private String password;

    private Boolean is_deleted;

    private Role role;


}
