
package de.ziad.abschlussprojekt.repository;

import de.ziad.abschlussprojekt.model.Anzeige;
import de.ziad.abschlussprojekt.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author User
 */
public interface AnzeigeRepository extends JpaRepository<Anzeige, Long> {
    
    public List<Anzeige> findByAnbieter(User user) ;     
}
