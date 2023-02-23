package com.campuscollaborate.auth;

import com.campuscollaborate.utility.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String given_name;
    private  String last_name;
    private Date dob;
    private  String education_level;
    private  String course_of_study;
    private  String email;
    private String password;
    private  String phone;
    @Enumerated(EnumType.STRING)
    private  Role role;
}
