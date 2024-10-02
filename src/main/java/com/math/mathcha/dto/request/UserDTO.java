package com.math.mathcha.dto.request;


import com.math.mathcha.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private int user_id;

    private String username;

    private String email;

    private String phone_number;

    private String address;

    private String password;

    private Boolean is_deleted;

    private Role role;


}
