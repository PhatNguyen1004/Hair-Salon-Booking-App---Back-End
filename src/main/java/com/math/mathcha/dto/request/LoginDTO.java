package com.math.mathcha.dto.request;

import jakarta.validation.constraints.NotBlank;

public class LoginDTO {
    @NotBlank(message = "Username can't be blank")
    private String User_name;

    @NotBlank(message = "Password can't be blank")
    private String Password;

    public String getUsername() {
        return User_name;
    }

    public void setUsername(String username) {
        User_name = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
