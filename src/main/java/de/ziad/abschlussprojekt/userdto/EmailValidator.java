package de.ziad.abschlussprojekt.userdto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author tlubowiecki
 */
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    private static final String PATTERN_STR = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern PATTERN = Pattern.compile(PATTERN_STR);
    
    @Override
    public boolean isValid(String email, ConstraintValidatorContext ctx) {
        
        Matcher matcher = PATTERN.matcher(email);
        return matcher.matches();
    }
}
