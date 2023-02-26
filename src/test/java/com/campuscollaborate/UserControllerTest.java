package com.campuscollaborate;

import com.campuscollaborate.controller.UserController;
import com.campuscollaborate.dto.UserDto;
import com.campuscollaborate.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Autowired
    @Mock
    private UserService userService;

    @InjectMocks
    @Autowired
    private UserController userController;

    @Test
    public void testGetAllUsers() {
        // Create a list of UserDto objects to return from the mocked service

    }

    @Test
    public void testGetAllUsersEmpty() {
        // Configure the mocked service to return an empty list
        when(userService.getAllUsers()).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<UserDto>> response = userController.getAllUsers();

        // Verify that the response is not found
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
