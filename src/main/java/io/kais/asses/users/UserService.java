package io.kais.asses.users;

import io.kais.asses.exceptions.UserNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
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

    public User update(User user) {
        User u = this.repository.save(user);
        return u;
    }

    public Boolean delete(Integer id) {
        User user = this.getById(id);
        this.repository.delete(user);
        return true;

    }

    public User create(User user) {
        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        return this.repository.save(user);
    }

    public List<User> saveAll(List<User> users) {
        users.stream().forEach(u -> {
            String encodedPassword = new BCryptPasswordEncoder().encode(u.getPassword());
            u.setPassword(encodedPassword);
        });


        return this.repository.saveAll(users);
    }


}
