
package de.ziad.abschlussprojekt.repository;

import de.ziad.abschlussprojekt.model.Kategorie;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author User
 */
public interface KategorieRepository extends JpaRepository<Kategorie, Long> {
    
    
    List<Kategorie> findByParentIsNull() ;
    
   List<Kategorie> findByParentIsNotNull() ;
            
            
            
  }
