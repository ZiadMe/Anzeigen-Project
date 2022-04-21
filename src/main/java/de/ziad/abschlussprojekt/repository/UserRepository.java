
package de.ziad.abschlussprojekt.repository;

import de.ziad.abschlussprojekt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author User
 */
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUserName(String userName);
    
    
    
}
