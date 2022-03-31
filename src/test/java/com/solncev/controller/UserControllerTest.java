package com.solncev.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solncev.Application;
import com.solncev.dto.CreateUserDto;
import com.solncev.dto.UserDto;
import com.solncev.model.User;
import com.solncev.repository.UserRepository;
import com.solncev.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
//@ActiveProfiles("test")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @MockBean
    private UserService userService;

    private final ObjectMapper mapper = new ObjectMapper();

    @Before
    public void init() {
        User user = new User();
        user.setEmail("m@mail.ru");
        user.setName("Ivan");
        user.setPassword("password");
        userRepository.save(user);
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(
                        get("/user")
                                .contentType(MediaType.APPLICATION_JSON)
                                .with(httpBasic("user", "password"))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Ivan"));
    }

    @Test
    public void testSignUp() throws Exception {
        mockMvc.perform(get("/sign_up"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("sign_up"));
    }

    @Test
    public void testRegistration() throws Exception {
        CreateUserDto createUserDto = new CreateUserDto();
        createUserDto.setName("Boris");
        createUserDto.setPassword("testTEST");
        createUserDto.setEmail("boris@mail.ru");
        given(userService.signUp(any(CreateUserDto.class), anyString())).willReturn(new UserDto(1, "Boris", "boris@mail.ru"));
        mockMvc.perform(post("/sign_up")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .flashAttr("user", createUserDto)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("sign_up_success"));
    }
}
