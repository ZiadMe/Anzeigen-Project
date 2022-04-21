
package de.ziad.abschlussprojekt.repository;

import de.ziad.abschlussprojekt.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author User
 */
public interface AppUserRepository extends CrudRepository<User, Long> {
    
}
