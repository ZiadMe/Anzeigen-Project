
package de.ziad.abschlussprojekt.configuration;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 *
 * @author User
 */
public class CustomAuthenticationHandler implements AuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy() ;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        
        String auth = authentication.getAuthorities().stream().findFirst().get().toString();
        
        if(auth.equals("ROLE_ADMIN")) { // granted authority is expected not a string
            redirectStrategy.sendRedirect(request, response, "/admin/welcome_admin");
        }else if(auth.equals("ROLE_GAST")) { // granted authority is expected not a string
           redirectStrategy.sendRedirect(request, response, "/gast/welcome_gast"); 
        } else {
            throw new RuntimeException() ;
        }
    }
    
}
