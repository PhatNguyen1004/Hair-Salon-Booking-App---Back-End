package com.math.mathcha.mapper;


import com.math.mathcha.dto.request.UserDTO;
import com.math.mathcha.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserDTO mapToUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setUser_id(user.getUser_id());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone_number(user.getPhone_number());
        userDTO.setAddress(user.getAddress());
        userDTO.setPassword(user.getPassword());
        userDTO.setIs_deleted(user.getIs_deleted());
        userDTO.setRole(user.getRole());
        return userDTO;

    }
    public static User mapToUser(UserDTO userDTO){
        User user = new User();
        user.setUser_id(userDTO.getUser_id());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPhone_number(userDTO.getPhone_number());
        user.setAddress(userDTO.getAddress());
        user.setPassword(userDTO.getPassword());
        user.setIs_deleted(userDTO.getIs_deleted());
        user.setRole(userDTO.getRole());
        return user;

    }
}
