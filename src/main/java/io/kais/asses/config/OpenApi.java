package io.kais.asses.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        name = "basicAuth",
        scheme = "basic")
public class OpenApi {


    @Bean
    public OpenAPI customOpenAPI() {
//        Tag t = new Tag();
//        t.setName("Identification&Authentication");
        return new OpenAPI()
                .info(new Info()
                        .title("User management service")
                        .version("1.0")
                        .description("Set of APIs to perform CRUD operation of users"));
//                .addTagsItem(t);

    }
}
