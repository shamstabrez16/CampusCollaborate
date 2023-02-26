package com.campuscollaborate.service;

import com.campuscollaborate.dto.UserDto;
import com.campuscollaborate.entity.ProjectEntity;
import com.campuscollaborate.entity.UserEntity;
import com.campuscollaborate.helper.Mapper;
import com.campuscollaborate.repository.ProjectRepository;
import com.campuscollaborate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectRepository projectRepository;

    public List<UserDto> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        List<UserDto> usersDto = new ArrayList<>();
        for (UserEntity user : users ) {
            usersDto.add(Mapper.userEntityToUserDTO(user));
        }
        return usersDto;
    }

    public UserDto getUserById(Long userId) {
        Optional<UserEntity> user=  userRepository.findByUserId(userId);
        UserDto userDTO = null;
        if(user!=null && user.isPresent()){
            userDTO =Mapper.userEntityToUserDTOOOptional(user);
        }
        return userDTO;
        

    }

    public Optional<UserEntity> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<ProjectEntity> getProjectsByUserId(int userId) {
        return projectRepository.findByUserUserId(userId);
    }

    public List<ProjectEntity> getProjectsByUserEmail(String email) {

        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);
        if (userEntityOptional.isPresent()) {
            UserEntity user = userEntityOptional.get();
            return user.getProjects();
        } else {
            return  null;
        }

    }
    public UserEntity addUser(UserEntity user) {
      return  userRepository.save(user);
    }

    public UserEntity updateUser(UserEntity userEntity) {
        Optional<UserEntity> updatedUser = userRepository.findByEmail(userEntity.getEmail());
        if (updatedUser.isPresent()) {
            UserEntity user = updatedUser.get();
            user.setRole(userEntity.getRole());
            user.setCourseOfStudy(userEntity.getCourseOfStudy());
            user.setEducationLevel(userEntity.getEducationLevel());
            user.setPhone(userEntity.getPhone());
            user.setDob(userEntity.getDob());
            user.setLastName(userEntity.getLastName());
            user.setGivenName(userEntity.getGivenName());
            return userRepository.save(user);
        }
        else{
            return null;
        }
    }

    public Boolean deleteUser(String userEmail) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(userEmail);
        if(userEntity.isPresent()){
           return userRepository.deleteByEmail(userEmail);

        }
        else {
            return false;
        }

    }
}
