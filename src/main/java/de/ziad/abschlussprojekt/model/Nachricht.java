
package de.ziad.abschlussprojekt.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author User
 */
@Entity
@Table(name = "nachricht")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Nachricht implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nText ;
    
    private LocalDateTime erstellungsdatum ;
    
    @ManyToOne
    private User absender ;
    
    @ManyToOne
    private User empfaenger ;
    
    @ManyToOne
    private Anzeige anzeige ;
    
    
    
    
    
    
}
