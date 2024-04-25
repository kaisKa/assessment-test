package io.kais.asses.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.kais.asses.exceptions.UserNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This is the service layer in case needed to handle extra logic
 * right now it is just accessing the data layer and perform encoding to the password once saved
 */
@Service
public class UserService {
    private final UserRepository repository;
    private final ObjectMapper objectMapper;

    public UserService(UserRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }


    public List<User> getAll() {
        return this.repository.findAll();
    }


    public User getById(Integer id) {
        var op = this.repository.findById(id);
        if (op.isEmpty())
            throw new UserNotFoundException("user Not found");
        else return op.get();
    }

    public User getByUserName(String userName) {
        var op = this.repository.findByUserName(userName);
        if (op.isEmpty())
            throw new UserNotFoundException("user Not found");
        else return op.get();
    }

    public UserDto update(UserDto user) {
        User updateUser = objectMapper.convertValue(user,User.class);
        User u = this.repository.save(updateUser);
        return objectMapper.convertValue(u,UserDto.class);
    }

    public Boolean delete(Integer id) {
        User user = this.getById(id);
        this.repository.delete(user);
        return true;

    }

    public UserDto create(UserDto user) {
        User createUser = objectMapper.convertValue(user, User.class);
        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        return  objectMapper.convertValue( this.repository.save(createUser) , UserDto.class);
    }

    public List<User> saveAll(List<User> users) {
        users.stream().forEach(u -> {
            String encodedPassword = new BCryptPasswordEncoder().encode(u.getPassword());
            u.setPassword(encodedPassword);
        });


        return this.repository.saveAll(users);
    }


}
