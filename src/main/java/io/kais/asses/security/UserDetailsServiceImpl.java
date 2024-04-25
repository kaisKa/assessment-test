package io.kais.asses.security;

import io.kais.asses.users.User;
import io.kais.asses.users.UserRepository;
import io.kais.asses.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUserName(username);


//        if (userDetails == null) {
//            throw new UsernameNotFoundException("User with username: " + username + " not found");
//        }

        // Create and return the Spring Security UserDetails object
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserName())
                .password(user.getPassword())
                .roles("ADMIN")
                .passwordEncoder(p -> p)// Replace with appropriate roles from userDetails if available
                .build();
    }
}
