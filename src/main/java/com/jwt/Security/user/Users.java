package com.jwt.Security.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String given_name;
    private  String last_name;
    private Date dob;
    private  String education_level;
    private  String course_of_study;
    private  String email;
    private  String phone;

    @Transient
    private  String  password;

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", given_name='" + given_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", dob=" + dob +
                ", education_level='" + education_level + '\'' +
                ", course_of_study='" + course_of_study + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

}
