package de.ziad.abschlussprojekt.service;

import de.ziad.abschlussprojekt.model.User;
import de.ziad.abschlussprojekt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author User
 */
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserService service;
    
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        User user = service.findByUserName(username);
        if(user == null) {
            throw new UsernameNotFoundException("Kein Benutzer mit dem Namen " + username + " gefunden.");
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserName())
                .password(user.getPassword())
                .disabled(!user.isVerified())
                .accountExpired(false)
                .accountLocked(false)
                .roles(user.getRole().toString())
                .build();
        
    }
}
