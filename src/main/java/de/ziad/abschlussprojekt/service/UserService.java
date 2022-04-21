
package de.ziad.abschlussprojekt.service;

import de.ziad.abschlussprojekt.model.Anzeige;
import de.ziad.abschlussprojekt.model.Nachricht;
import de.ziad.abschlussprojekt.model.Role;
import de.ziad.abschlussprojekt.model.User;
import de.ziad.abschlussprojekt.repository.AnzeigeRepository;
import de.ziad.abschlussprojekt.repository.UserRepository;
import de.ziad.abschlussprojekt.userdto.UserDto;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class UserService {
    
    
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    AnzeigeRepository anzeigeRepository ;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public User createUser(UserDto dto) {
        User user = new User(); 
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setUserName(dto.getUserName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.GAST);
        
        return userRepository.save(user);
    }
    
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
    
    public List<User> getAll(){
     return (List<User>) userRepository.findAll() ;
    }
    
    public User getById(Long id) {
    return userRepository.findById(id).orElseThrow();
    }
    
    public void save(User user) {
     userRepository.save(user) ;
    }
    
    public void delete(User user) {
     userRepository.delete(user) ;
    }
    
    
    public List <Anzeige> getMyAnzeigen(User user) {
        
        List<Anzeige> myAnzeigen = null ;
        List<Anzeige> allAnzeigen = anzeigeRepository.findAll() ;
        
    
        for(Anzeige anzeige : allAnzeigen) {
            if(anzeige.getAnbieter().equals(user)) { // ???
                
                myAnzeigen.add(anzeige) ;
            }
        }
        return myAnzeigen ;
    
    } 
    

    
    public List<User> getmyPartners(User currentUser, List<Nachricht> nachrichten) {
        Set<Long> userId = null ;
        List<User> myPartner = null ;
        User user = currentUser ;
        
        
        for(Nachricht nachricht : user.getVerschickteNachrichten()) {
           
            userId.add(nachricht.getEmpfaenger().getId()) ;  
        }
        for(Nachricht nachricht : user.getEmpfangenenNachrichten()) {
            
            userId.add(nachricht.getAbsender().getId()) ;
        }
        
        for(Long id : userId) {
            myPartner.add(userRepository.findById(id).get()) ;
          
        }
    
    return myPartner ;
    }
    
    public List<Anzeige> getmyGespraeche(User currentUser, User partner) {
        Set<Long> anzeigeId = null ;
        List<Anzeige> myAnzeigen = null ;
        User user = currentUser ;
        User mypartner = partner ;
        List<Nachricht> myNachrichten = null ;
        
        for(Nachricht nachricht : user.getVerschickteNachrichten()) {
            myNachrichten.add(nachricht) ;
        }
        for(Nachricht nachricht : user.getEmpfangenenNachrichten()) {
            myNachrichten.add(nachricht) ;
        }
        
        for(Nachricht nachricht : myNachrichten) {
          //  anzeigeId.add(nachricht.getAnzeige().getId()) ;
        }
    
    return myAnzeigen ;
    }
    
    
    
}
