
package de.ziad.abschlussprojekt.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author User
 */
@Entity
@Table(name = "anzeige")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude="anbieter")
public class Anzeige implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String title ;
    
    private String body ;
    
    private String postalCode ;
    
    private LocalDateTime erstellungsdatum ;
    
    @Column(nullable = true)
    private String avatar;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private User anbieter ;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Kategorie kategorie ;
    
//    @Enumerated(EnumType.ORDINAL)
//    private Status status = Status.INAKTIV ;
    
    private boolean aktive ;
    
    @OneToMany(mappedBy = "anzeige" , cascade = CascadeType.ALL)
    private List<Nachricht> nachrichten ;
    
    
    
}
