package io.kais.asses.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.kais.asses.security.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserService userService;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @Test
    @WithMockUser(username = "kais", password = "kaiskiki", roles = "USER")
    public void test_getAllUser_should_return_all_users() throws Exception {

        User user = new User(0, "kikika", "123456", "kais", "Alkotamy", "male");
        given(this.userService.getAll()).willReturn(List.of(user));

        var result = mockMvc.perform(get("/api/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath ("$", hasSize(1)))
                .andExpect(jsonPath("$").isArray())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "kais", password = "kaiskiki", roles = "USER")
    public void test_getUserById_should_return_all_users() throws Exception {

        User user = new User(0, "kikika", "123456", "kais", "Alkotamy", "male");
        given(this.userService.getById(0)).willReturn(user);

        var result = mockMvc.perform(get("/api/users/0").with(httpBasic("kais","kaiskiki")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.id", is(0)))
                .andExpect(jsonPath("$.userName", is("kikika")))
                .andExpect(jsonPath("$.password").isNotEmpty())
                .andReturn();
    }

    @Test
    public void test_createUser_should_create_user() throws Exception {

        User user = new User(0, "kikika", "123456", "kais", "Alkotamy", "male");
        given(this.userService.create(user)).willReturn(user);

        var result = mockMvc.perform(post("/api/users/")
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$").isNotEmpty())
//                .andExpect(jsonPath("$.id", is(0)))
//                .andExpect(jsonPath("$.userName", is("kikika")))
//                .andExpect(jsonPath("$.password").isNotEmpty())
                .andReturn();
    }

    @Test
    public void test_updateUser_should_update_user() throws Exception {

        User user = new User(0, "kikika", "123456", "kais", "Alkotamy", "male");
        given(this.userService.update(user)).willReturn(user);

        var result = mockMvc.perform(put("/api/users/")
                        .content(objectMapper.writeValueAsString(user))

                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$").isNotEmpty())
//                .andExpect(jsonPath("$.id", is(0)))
//                .andExpect(jsonPath("$.userName", is("kikika")))
//                .andExpect(jsonPath("$.password").isNotEmpty())
                .andReturn();
        System.out.printf("fd");
    }



    @Test
    @WithMockUser(username = "kais", password = "kaiskiki", roles = "USER")
    public void test_deleteUser_should_delete_user() throws Exception {

        User user = new User(0, "kikika", "123456", "kais", "Alkotamy", "male");

        var result = mockMvc.perform(delete("/api/users/" + user.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}