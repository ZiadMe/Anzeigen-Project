
package de.ziad.abschlussprojekt.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author User
 */
@Entity
@Table(name = "kategorie")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Kategorie implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name ;
    
    private boolean aktive ;
    
    @ManyToOne(optional = true , cascade = CascadeType.ALL)
    private Kategorie parent ;
    
   @OneToMany(mappedBy="parent", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<Kategorie> children = new ArrayList<>();
    
    @OneToMany(mappedBy = "kategorie", cascade = CascadeType.ALL)
    private List<Anzeige> anzeigen = new ArrayList<>();
   
    
}
