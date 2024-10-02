package com.math.mathcha.entity;

import com.math.mathcha.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int user_id;
    @Column(name = "username")
    private String username;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "phone_number", unique = true)
    private String phone_number;
    @Column(name = "address")
    private String address;
    @Column(name = "password")
    private String password;
    @Column(name = "is_deleted", columnDefinition = "TINYINT(1)")
    private Boolean is_deleted;
    @Enumerated(EnumType.STRING)
    Role role;
}


