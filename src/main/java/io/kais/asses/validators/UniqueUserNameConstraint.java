package io.kais.asses.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueUserNameValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUserNameConstraint {
    String message() default "userName must be unique and not null";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
