
package de.ziad.abschlussprojekt.service;

import de.ziad.abschlussprojekt.model.Anzeige;
import de.ziad.abschlussprojekt.model.Nachricht;
import de.ziad.abschlussprojekt.model.User;
import de.ziad.abschlussprojekt.repository.AnzeigeRepository;
import de.ziad.abschlussprojekt.repository.NachrichtRepository;
import de.ziad.abschlussprojekt.repository.UserRepository;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class AnzeigeService {
    
    
    
    
    @Autowired
    UserRepository userRepository ;
    
    @Autowired
    AnzeigeRepository anzeigeRepository ;
    
    @Autowired
    NachrichtRepository nachrichtRepository ;
    
    public List<Anzeige> getAll() {
        return  anzeigeRepository.findAll() ;
    }
    
    public Optional<Anzeige> getById(Long id) {
    
        return anzeigeRepository.findById(id) ;
    
    
    }
    public void save(Anzeige anzeige) {
    
         anzeigeRepository.save(anzeige) ;
    }
    
    public void delete(Anzeige anzeige) {
    
        anzeigeRepository.delete(anzeige) ;
    }
    
    public List<Anzeige> getAnzeigeByAnbieter(User user){
        
        return anzeigeRepository.findByAnbieter(user) ;
    
    }
    
    
}
