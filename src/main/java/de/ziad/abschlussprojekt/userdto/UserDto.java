
package de.ziad.abschlussprojekt.userdto;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author User
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@PasswordMatches(message = "{valid.passwordMatch}")
public class UserDto {
    
    @NotEmpty(message="{valid.notEmpty}")
    private String firstName;
    
    @NotEmpty(message="{valid.notEmpty}")
    private String lastName;
    
    @NotEmpty(message="{valid.notEmpty}")
    private String userName;
    
    @ValidEmail(message = "{valid.email}")
    private String email;
    
    @ValidPassword(message="{valid.password}")
    private String password;
    
    @NotEmpty(message="{valid.notEmpty}")
    private String confirmPassword;
}
