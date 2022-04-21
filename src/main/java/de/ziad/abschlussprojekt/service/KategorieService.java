
package de.ziad.abschlussprojekt.service;

import de.ziad.abschlussprojekt.model.Anzeige;
import de.ziad.abschlussprojekt.model.Kategorie;
import de.ziad.abschlussprojekt.repository.AnzeigeRepository;
import de.ziad.abschlussprojekt.repository.KategorieRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class KategorieService {
    
    @Autowired
    KategorieRepository kategorieRepository ;
    
    
    
    public List<Kategorie> getAll() {
        return (List<Kategorie>) kategorieRepository.findAll() ;
    }
    
    public List<Kategorie> getHauptKategorie() {
    
      return  kategorieRepository.findByParentIsNull();
    
    }
    public Kategorie getById(Long id) {
    
        return kategorieRepository.findById(id).orElseThrow() ;
    
    
    }
    public Kategorie save(Kategorie kategorie) {
    
       return kategorieRepository.save(kategorie) ;
    }
    
    public void delete(Kategorie kategorie) {
    
        kategorieRepository.delete(kategorie) ;
    }
    
    public List<Kategorie> getSubKategorien() {
    
    
        return kategorieRepository.findByParentIsNotNull();
      
    }
    
}
