package com.math.mathcha.dto.response;


import com.math.mathcha.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResUserDTO {
    private int user_id;

    private String username;

    private String email;

    private String phone_number;

    private String address;

    private String password;

    private Boolean is_deleted;

    private Role role;
}
