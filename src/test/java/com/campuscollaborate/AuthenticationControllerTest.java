package com.campuscollaborate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.campuscollaborate.controller.AuthenticationController;
import com.campuscollaborate.requestEntity.AuthenticationRequest;
import com.campuscollaborate.requestEntity.RegisterRequest;
import com.campuscollaborate.responseEntity.AuthenticationResponse;
import com.campuscollaborate.service.AuthenticationService;
import com.campuscollaborate.utility.Role;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

@Slf4j
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
@AutoConfigureMockMvc
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
public class AuthenticationControllerTest {

    private MockMvc mockMvc;

    private AuthenticationService authenticationService;

    private AuthenticationController authenticationController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext,
               RestDocumentationContextProvider restDocumentation) throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .alwaysDo(document("{class-name}/{method-name}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())))
                .build();
        objectMapper = new ObjectMapper();
        authenticationService = mock(AuthenticationService.class);
        authenticationController = new AuthenticationController(authenticationService);
    }

    @Test
    void testRegisterUser() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setPassword("password");
        registerRequest.setGivenName("shams");
        registerRequest.setEmail("shamsi@wiu.edu");
        registerRequest.setDob(new Date("02/01/1994"));
        registerRequest.setPhone("9876543210");
        registerRequest.setLastName("mohammad");
        registerRequest.setRole(Role.USER);
        registerRequest.setEducationLevel("masters");
        registerRequest.setCourseOfStudy("CSE");
        String body = objectMapper.writeValueAsString(registerRequest);

        AuthenticationResponse authenticationResponse= null; //= new AuthenticationResponse("token", HttpStatus.OK);
        when(authenticationService.register(registerRequest)).thenReturn(authenticationResponse);
        ResultActions resultActions;
        resultActions = mockMvc.perform(post("/api/v1/auth/register")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("{methodName}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())));
    }
}