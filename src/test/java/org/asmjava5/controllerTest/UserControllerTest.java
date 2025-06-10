package org.asmjava5.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.asmjava5.Authenticate.data.dto.request.RegisterDtoRequest;
import org.asmjava5.controller.UserController;
import org.asmjava5.convert.UserMapstruct;
import org.asmjava5.data.dto.request.UserDtoRequest;
import org.asmjava5.data.dto.request.update.UserUpdateRequest;
import org.asmjava5.data.dto.response.UserDtoResponse;
import org.asmjava5.data.entity.User;
import org.asmjava5.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private UserMapstruct userMapstruct;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testGetAllUsersSuccess() throws Exception {
        // Arrange
        User user1 = new User();
        user1.setUserId(1L);
        user1.setUsername("user1");
        user1.setRole("USER");

        User user2 = new User();
        user2.setUserId(2L);
        user2.setUsername("user2");
        user2.setRole("ADMIN");

        List<UserDtoResponse> users = userMapstruct.toUserDTOResponseList(Arrays.asList(user1, user2));

        when(userService.getUsers()).thenReturn(users);

        // Act & Assert
        mockMvc.perform(get("/api/v1/user/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].userId").value(1))
                .andExpect(jsonPath("$.data[0].username").value("user1"))
                .andExpect(jsonPath("$.data[0].role").value("USER"))
                .andExpect(jsonPath("$.data[1].userId").value(2))
                .andExpect(jsonPath("$.data[1].username").value("user2"))
                .andExpect(jsonPath("$.data[1].role").value("ADMIN"));
    }

    @Test
    void testGetUserByUsernameSuccess() throws Exception {
        // Arrange
        User user = new User();
        user.setUserId(1L);
        user.setUsername("testuser");
        user.setRole("USER");

        when(userService.getUserByUserName("testuser")).thenReturn(userMapstruct.toUserDTOResponse(user));

        // Act & Assert
        mockMvc.perform(get("/api/v1/user/name")
                .param("username", "testuser"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.data.userId").value(1))
                .andExpect(jsonPath("$.data.username").value("testuser"))
                .andExpect(jsonPath("$.data.role").value("USER"));
    }

    @Test
    void testSaveUserSuccess() throws Exception {
        // Arrange
        RegisterDtoRequest userRequest = new RegisterDtoRequest();
        userRequest.setUsername("newuser");
        userRequest.setEmail("newuser@gmail.com");
        userRequest.setPassword("password123");
        userRequest.setConfirmPassword("password123");

        User savedUser = new User();
        savedUser.setUserId(1L);
        savedUser.setUsername("newuser");
        savedUser.setRole("USER");

        when(userService.saveUser(any(UserDtoRequest.class))).thenReturn(true);

        // Act & Assert
        mockMvc.perform(post("/api/v1/user/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Successfully saved user"))
                .andExpect(jsonPath("$.data.userId").value(1))
                .andExpect(jsonPath("$.data.username").value("newuser"))
                .andExpect(jsonPath("$.data.role").value("USER"));
    }

    @Test
    void testUpdateUserRoleSuccess() throws Exception {

        UserUpdateRequest updateRequest = new UserUpdateRequest();
        updateRequest.setRole("ADMIN");

        User updatedUser = new User();
        updatedUser.setUserId(1L);
        updatedUser.setUsername("testuser");
        updatedUser.setRole("ADMIN");

        when(userService.userUpdateRequest(any(UserUpdateRequest.class))).thenReturn(true);

        // Act & Assert
        mockMvc.perform(post("/api/v1/user/update/userm")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Successfully saved user"))
                .andExpect(jsonPath("$.data.role").value("ADMIN"));
    }


    @Test
    void testDeleteUserSuccess() throws Exception {
        // Arrange
        when(userService.deleteUserByUserName("testuser")).thenReturn(true);

        // Act & Assert
        mockMvc.perform(delete("/api/v1/user/delete")
                .param("username", "testuser"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Delete success"));
    }

    @Test
    void testGetUserByUsernameNotFound() throws Exception {
        // Arrange
        when(userService.getUserByUserName("nonexistent")).thenReturn(null);

        // Act & Assert
        mockMvc.perform(get("/api/v1/user/name")
                .param("username", "nonexistent"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void testSaveUserWithException() throws Exception {
        // Arrange
        UserDtoRequest userRequest = new UserDtoRequest();
        userRequest.setUsername("duplicateuser");

        when(userService.saveUser(any(UserDtoRequest.class)))
                .thenThrow(new RuntimeException("Username already exists"));

        // Act & Assert
        mockMvc.perform(post("/api/v1/user/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isInternalServerError());
    }
} 