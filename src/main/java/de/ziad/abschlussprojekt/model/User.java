
package de.ziad.abschlussprojekt.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 *
 * @author User
 */
@Data
@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column( length = 50)
    @NotEmpty(message = "{valid.notEmpty}")
    private String firstName ;
    
    @Column(length = 50)
    @NotEmpty(message = "{valid.notEmpty}")
    private String lastName ;
    
    @Column(length = 50)
    @NotEmpty(message = "{valid.notEmpty}")
    private String userName ;
    
    @Column(length = 200)
    @NotEmpty(message = "{valid.notEmpty}")
    private String password ;
    
    @Column(unique = true,length = 50)
    @NotEmpty(message = "{valid.notEmpty}")
    private String email ;
    
    @OneToMany(mappedBy = "anbieter", cascade = CascadeType.ALL)
    private List<Anzeige> anzeigen = new ArrayList<>();
   
    @OneToMany(mappedBy = "empfaenger", cascade = CascadeType.ALL)
    private List<Nachricht> verschickteNachrichten = new ArrayList<>();
  
    @OneToMany(mappedBy = "absender", cascade = CascadeType.ALL)
    private List<Nachricht> empfangenenNachrichten = new ArrayList<>();
    
    @Column(nullable = true)
    private String avatar;
   
    private String postalCode ;
   
    private boolean verified;
    
    private boolean disabled = false ;
    
  //  private List<String> blockedUsers = new ArrayList<String>() ;
    
    private int radius ;
    
    private Duration duration ;
  
    @Enumerated(EnumType.ORDINAL)
    private Role role ;
    
    
    
    
    
    
}
