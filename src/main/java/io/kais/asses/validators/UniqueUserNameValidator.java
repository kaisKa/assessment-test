package io.kais.asses.validators;

import io.kais.asses.exceptions.UserNotFoundException;
import io.kais.asses.users.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserNameValidator implements
        ConstraintValidator<UniqueUserNameConstraint, String> {

    private final UserService userService;

    public UniqueUserNameValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(UniqueUserNameConstraint userName) {
    }

    @Override
    public boolean isValid(String userName,
                           ConstraintValidatorContext cxt) {
        try {
            var user = userService.getByUserName(userName);
            return userName != null && user == null;
        }catch (UserNotFoundException e){
            return userName != null;
        }
    }

}
