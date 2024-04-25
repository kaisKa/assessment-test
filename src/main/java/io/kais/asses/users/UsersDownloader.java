package io.kais.asses.users;

import aj.org.objectweb.asm.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

/**
 * This is a loader class to load populate some data upon start up from a json file
 */
@Component
public class UsersDownloader implements CommandLineRunner {
    private final ObjectMapper objectMapper;
    private final UserService service;

    public UsersDownloader(ObjectMapper objectMapper, UserService service) {
        this.objectMapper = objectMapper;
        this.service = service;
    }


    @Override
    public void run(String... args) throws Exception {
        String usersPath = "/data/users.json";
        try(InputStream inputStream = TypeReference.class.getResourceAsStream(usersPath)){
            var response = objectMapper.readValue(inputStream, users.class);
            this.service.saveAll(response.users);
         }catch (IOException ex){
            ex.printStackTrace();
            throw  new RuntimeException("Failed to read json data");
        }
    }
}
