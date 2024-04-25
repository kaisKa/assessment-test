package io.kais.asses.users;

import aj.org.objectweb.asm.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class UsersDownloader implements CommandLineRunner {
    private final ObjectMapper objectMapper;
    private final UserRepository repository;

    public UsersDownloader(ObjectMapper objectMapper, UserRepository repository) {
        this.objectMapper = objectMapper;
        this.repository = repository;
    }


    @Override
    public void run(String... args) throws Exception {
        String usersPath = "/data/users.json";
        try(InputStream inputStream = TypeReference.class.getResourceAsStream(usersPath)){
            var response = objectMapper.readValue(inputStream, users.class);
            this.repository.saveAll(response.users);
         }catch (IOException ex){
            ex.printStackTrace();
            throw  new RuntimeException("Failed to read json data");
        }
    }
}
