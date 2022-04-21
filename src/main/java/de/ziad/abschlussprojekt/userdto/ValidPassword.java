package de.ziad.abschlussprojekt.userdto;

import java.lang.annotation.Documented;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author User
 */

@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
@Documented
public @interface ValidPassword {
    
    public String message() default "Invalid Password";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
    
}
