package io.kais.asses.users;

import io.kais.asses.exceptions.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @Mock
    private UserService userService;


    @Test
    void test_getAll_return_all_user() {

        User user0 = new User(0, "kikika", "123456", "kais", "Alkotamy", "male");
        User user1 = new User(1, "user1", "123456", "user1", "Alkotamy", "male");
        given(userService.getAll()).willReturn(Arrays.asList(user0, user1));
        var users = userService.getAll();
        assertEquals(users.size(), 2);
        assertEquals(users.get(0).getUserName(), "kikika");
        assertEquals(users.get(1).getUserName(), "user1");
        assertNotNull(users.get(0).getPassword() );
        assertNotNull(users.get(1).getPassword() );
    }

    @Test
    void test_getById_return_user() {

        User user0 = new User(0, "kikika", "123456", "kais", "Alkotamy", "male");
        given(userService.getById(0)).willReturn(user0);
        assertEquals(userService.getById(0).getUserName(), "kikika");
        assertNotNull(userService.getById(0).getPassword());
    }

    @Test
    void test_getBy_invalid_id_will_throw_not_found_ex() {

        User user0 = new User(0, "kikika", "123456", "kais", "Alkotamy", "male");
        given(userService.getById(0)).willThrow(new UserNotFoundException("User not found"));
        Exception exception = assertThrows(UserNotFoundException.class,()->{
            userService.getById(0);
        });

        assertTrue(exception.getMessage().contains("User not found"));
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void test_create_verify_creating_once() {

        UserDto user0 = new UserDto(0, "kikika", "123456", "kais", "Alkotamy", "male");
        userService.create(user0);
        verify(userService, times(1)).create(user0);
        ArgumentCaptor<UserDto> orderArgumentCaptor = ArgumentCaptor.forClass(UserDto.class);
        verify(userService).create(orderArgumentCaptor.capture());
        UserDto orderCreated = orderArgumentCaptor.getValue();
        assertNotNull(orderCreated.getUserName());
        assertEquals("kikika", orderCreated.getUserName());
    }
}