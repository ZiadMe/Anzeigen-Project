
package de.ziad.abschlussprojekt.controller;

import de.ziad.abschlussprojekt.model.Anzeige;
import de.ziad.abschlussprojekt.model.Kategorie;
import de.ziad.abschlussprojekt.model.User;
import de.ziad.abschlussprojekt.repository.AnzeigeRepository;
import de.ziad.abschlussprojekt.service.AnzeigeService;
import de.ziad.abschlussprojekt.service.KategorieService;
import de.ziad.abschlussprojekt.service.UserService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author User
 */
@Controller
public class GastController {
    
    
  //  public Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private AnzeigeService anzeigeService ;
    
    @Autowired
    private KategorieService kategorieService ;
    
   
    
    @RequestMapping("/gast/welcome_gast")
    public String page(Model model) {
        model.addAttribute("anzeigenList", anzeigeService.getAll());
        return "welcome_gast";
    }
    
    @RequestMapping("/gast/options")
    public String optionsPage(Model model) {
       
        return "options";
    }
    
    @GetMapping("/gast/welcome_gast/gastanzeigedetails/{id}")
    public String showAnzeigeDetails(@PathVariable("id") Long id, Model model) {
        Optional<Anzeige> opt = anzeigeService.getById(id);
        if(opt.isPresent()) {
            model.addAttribute("anzeige", opt.get());
            return "gastanzeigedetails";
        }
        return "error";
       
    }
    
    @GetMapping("/gast/myanzeigen/myanzeigendetails/{id}")
    public String showMyAnzeigeDetails(@PathVariable("id") Long id, Model model) {
        Optional<Anzeige> opt = anzeigeService.getById(id);
        if(opt.isPresent()) {
            model.addAttribute("anzeige", opt.get());
            return "myanzeigendetails";
        }
        return "error";
       
    }
    
     @GetMapping("/gast/logout")
    public String logoutAction(Model model) {
        
        return "/index";
    }
    
    
      //  showing my Anzeige :
    @RequestMapping("/gast/myanzeigen")
    public String showMyAnzeigenAction(Model model) {

       Authentication loggedUser = SecurityContextHolder.getContext().getAuthentication();
        String userName = loggedUser.getName() ;
        
        User user = userService.findByUserName(userName) ;
     
        model.addAttribute("myAnzeigenList", user.getAnzeigen()) ;
       
        return "myanzeigen";
    }
    
     //  Adding Anzeige :
    @GetMapping("/gast/myanzeigen/gastaddanzeige")
    public String addAnzeigenAction( Model model) {
        Authentication loggedUser = SecurityContextHolder.getContext().getAuthentication();
        String userName = loggedUser.getName() ;
       
        

        User user = userService.findByUserName(userName) ;
    
        
        Anzeige anzeige = new Anzeige() ;
        
        anzeige.setAnbieter(user);
        anzeige.setPostalCode(user.getPostalCode());
        anzeige.setErstellungsdatum(LocalDateTime.now());
        
       
       model.addAttribute("anzeige", anzeige) ;
       model.addAttribute("kategorienList", kategorieService.getHauptKategorie());
       
        return "gastaddanzeige";
    }
    
    @PostMapping("/gast/myanzeigen/gastaddanzeige")
    public String addingAnzeigenAction(Anzeige anzeige, @RequestParam(value = "kategorie.id", required = true) Kategorie kategorie) {
        
        
    Authentication loggedUser = SecurityContextHolder.getContext().getAuthentication();
        String userName = loggedUser.getName() ;
        User user = userService.findByUserName(userName) ;
        
         anzeige.setAnbieter(user);
        anzeige.setPostalCode(user.getPostalCode());
        anzeige.setErstellungsdatum(LocalDateTime.now());
        anzeige.setKategorie(kategorie);
        
     anzeigeService.save(anzeige);
    
     
    return "redirect:/gast/myanzeigen" ;
    }
    
     @RequestMapping(value = "gast/myanzeigen/deleteanzeige/{id}", method ={RequestMethod.GET,RequestMethod.DELETE})
    public String deleteAnzeigeAction( Anzeige anzeige) {
        
        anzeigeService.delete(anzeige) ;
      
        return "redirect:/gast/myanzeigen";
    }
    
    
}
