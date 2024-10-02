package com.math.mathcha.controller.UserController;

import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.UserDTO;
import com.math.mathcha.dto.response.ResCreateUserDTO;
import com.math.mathcha.dto.response.ResUpdateUserDTO;
import com.math.mathcha.dto.response.ResUserDTO;
import com.math.mathcha.service.userService.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor

@SecurityRequirement(name = "brearerAuth")
@RequestMapping("/user")
@SecurityRequirement(name = "api")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private UserService userService;
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResCreateUserDTO> createUser(@Valid @RequestBody UserDTO userDTO) throws IdInvalidException {
        UserDTO savedUser = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.convertToResCreateUserDTO(savedUser));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get/{user_id}")
    public ResponseEntity<ResUserDTO> getUserById (@PathVariable("user_id") Integer user_id) throws IdInvalidException{
        UserDTO userDTO = userService.getUserById(user_id);
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.convertToResUserDTO(userDTO));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER') ")
    @GetMapping("/get/all")
    public ResponseEntity<List<ResUserDTO>> getUserAll(){
        List<ResUserDTO> user = userService.getUserAll();
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username : {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));
        return ResponseEntity.ok(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{user_id}")
    public ResponseEntity<ResUpdateUserDTO> updateUser(@RequestBody UserDTO updatedUser, @PathVariable("user_id") Integer user_id) throws IdInvalidException{
        UserDTO userDTO = userService.updateUser(updatedUser,user_id);
        return ResponseEntity.ok(this.userService.convertToResUpdateUserDTO(userDTO));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{user_id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("user_id") Integer user_id) throws IdInvalidException{
        UserDTO currentUser = this.userService.getUserById(user_id);
        this.userService.deleteUser(user_id);
        return ResponseEntity.ok(null);
    }

}
