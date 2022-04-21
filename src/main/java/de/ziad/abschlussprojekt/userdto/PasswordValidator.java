package de.ziad.abschlussprojekt.userdto;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.passay.LengthRule;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordData;
import org.passay.Rule;
import org.passay.RuleResult;

/**
 *
 * @author tlubowiecki
 */
public class PasswordValidator implements ConstraintValidator<ValidPassword, String>{

    @Override
    public boolean isValid(String password, ConstraintValidatorContext ctx) {
        
        List<Rule> rules = new ArrayList<>();
        rules.add(new LengthRule(6,24)); // Länge
        rules.add(new CharacterRule(EnglishCharacterData.UpperCase, 1)); // mind. 1 Großbuchstabe
        rules.add(new CharacterRule(EnglishCharacterData.LowerCase, 1)); // mind. 1 Kleinbuchstabe
        rules.add(new CharacterRule(EnglishCharacterData.Digit, 1)); // mind. eine Zahl
        rules.add(new CharacterRule(EnglishCharacterData.Special, 1)); // mind. ein Sonderzeichen
        
        final org.passay.PasswordValidator validator = new org.passay.PasswordValidator(rules);
        RuleResult result = validator.validate(new PasswordData(password));
        
        if(result.isValid()) {
            return true;
        }
        return false;
    }
}
