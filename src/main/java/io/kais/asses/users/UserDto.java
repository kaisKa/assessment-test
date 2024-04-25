package io.kais.asses.users;


import io.kais.asses.validators.UniqueUserNameConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * The Entity class that represent a user
 * with simple validation to showcase
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class UserDto {
    private Integer id;
    @UniqueUserNameConstraint
    private String userName;
    @NotBlank
    @Size(min = 6,  message = "Password length should be between 6 to 30 char ")
    private String password;
    private String firstName;
    private String lastName;
    private String gender;



}
