package com.math.mathcha.service.userService.Impl;

import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.UserDTO;
import com.math.mathcha.dto.response.ResCreateUserDTO;
import com.math.mathcha.dto.response.ResUpdateUserDTO;
import com.math.mathcha.dto.response.ResUserDTO;
import com.math.mathcha.entity.User;

import com.math.mathcha.mapper.UserMapper;
import com.math.mathcha.repository.UserRepository.UserRepository;
import com.math.mathcha.service.userService.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO createUser(UserDTO userDTO) throws IdInvalidException {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new IdInvalidException("Username " + userDTO.getUsername() + " đã tồn tại, vui lòng sử dụng email khác.");
        }
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = UserMapper.mapToUser(userDTO);
        user.setRole(userDTO.getRole());
        User savedUser= userRepository.save(user);
        return UserMapper.mapToUserDTO(savedUser);
    }

    @Override
    public UserDTO getUserById(Integer user_id) throws IdInvalidException {
        Optional<User> user = userRepository.findById(user_id);
        if (user.isPresent()) {
            return UserMapper.mapToUserDTO(user.get());
        }else{
            throw new IdInvalidException("User với id = " + user_id + " không tồn tại");
        }
    }

    @Override
    public List<ResUserDTO> getUserAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserMapper::mapToUserDTO)
                .map(this::convertToResUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(UserDTO updateUser, Integer user_id) {
        User user = userRepository.findById(user_id)
                .orElseThrow(()-> new RuntimeException("Topic "+user_id+" not found"));
     user.setUser_id(updateUser.getUser_id());
     user.setUsername(updateUser.getUsername());
     user.setEmail(updateUser.getEmail());
     user.setPhone_number(updateUser.getPhone_number());
     user.setAddress(updateUser.getAddress());
     user.setPassword(passwordEncoder.encode(updateUser.getPassword()));
     user.setIs_deleted(updateUser.getIs_deleted());
     user.setRole(updateUser.getRole());
        User updateUserObj = userRepository.save(user);
        return UserMapper.mapToUserDTO(updateUserObj);
    }

    @Override
    public void deleteUser(Integer user_id) throws IdInvalidException {
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new IdInvalidException("User với id = " + user_id + " không tồn tại"));
        userRepository.deleteById(user_id);
    }

    @Override
    public UserDTO handleGetUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(()->new RuntimeException("Not exits"+username));

        return UserMapper.mapToUserDTO(user);
    }

    public boolean isUsernameExist(String username) {
        return this.userRepository.existsByUsername(username);
    }
    @Override
    public ResCreateUserDTO convertToResCreateUserDTO(UserDTO user) {
        ResCreateUserDTO res = new ResCreateUserDTO();
        res.setUser_id(user.getUser_id());
        res.setUsername(user.getUsername());
        res.setEmail(user.getEmail());
        res.setPhone_number(user.getPhone_number());
        res.setAddress(user.getAddress());
        res.setIs_deleted(user.getIs_deleted());
        res.setRole(user.getRole());
        return res;
    }
    @Override
    public ResUpdateUserDTO convertToResUpdateUserDTO(UserDTO user) {
        ResUpdateUserDTO res = new ResUpdateUserDTO();
        res.setUser_id(user.getUser_id());
        res.setUsername(user.getUsername());
        res.setEmail(user.getEmail());
        res.setPhone_number(user.getPhone_number());
        res.setAddress(user.getAddress());
        res.setIs_deleted(user.getIs_deleted());
        res.setRole(user.getRole());
        return res;
    }
    @Override
    public ResUserDTO convertToResUserDTO(UserDTO user) {
        ResUserDTO res = new ResUserDTO();
        res.setUser_id(user.getUser_id());
       res.setUsername(user.getUsername());
       res.setEmail(user.getEmail());
       res.setPhone_number(user.getPhone_number());
       res.setAddress(user.getAddress());
       res.setIs_deleted(user.getIs_deleted());
       res.setRole(user.getRole());
        return res;
    }
}
