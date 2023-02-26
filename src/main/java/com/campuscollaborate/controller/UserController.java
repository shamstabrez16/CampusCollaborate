package com.campuscollaborate.controller;

import com.campuscollaborate.dto.UserDto;
import com.campuscollaborate.entity.ProjectEntity;
import com.campuscollaborate.entity.UserEntity;
import com.campuscollaborate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> usersDto=  userService.getAllUsers();
        if (usersDto != null && !usersDto.isEmpty()) {
            return ResponseEntity.ok().body(usersDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId) {
        UserDto userDTO= userService.getUserById(userId);
        if(userDTO!=null){
            return  ResponseEntity.ok().body(userDTO);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/email/{email}")
    public Optional<UserEntity> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/{userId}/projects")
    public List<ProjectEntity> getProjectsByUserId(@PathVariable int userId) {
        return userService.getProjectsByUserId(userId);
    }

    @GetMapping("/email/{email}/projects")
    public List<ProjectEntity> getProjectsByUserEmail(@PathVariable String email) {
        return userService.getProjectsByUserEmail(email);
    }

    //No Test case
    @PostMapping("")
    public UserEntity addUser(@RequestBody UserEntity user) {
        return userService.addUser(user);
    }

    @PutMapping("/{userId}")
    public UserEntity updateUser(@RequestBody UserEntity user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable String email) {
       if(userService.deleteUser(email)){
          return ResponseEntity.ok().body(true);
       }
       else {
           return ResponseEntity.ok().body(false);
       }
    }
}
