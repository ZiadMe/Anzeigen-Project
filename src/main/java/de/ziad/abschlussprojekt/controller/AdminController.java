
package de.ziad.abschlussprojekt.controller;

import de.ziad.abschlussprojekt.model.Anzeige;
import de.ziad.abschlussprojekt.model.Kategorie;
import de.ziad.abschlussprojekt.model.User;
import de.ziad.abschlussprojekt.service.AnzeigeService;
import de.ziad.abschlussprojekt.service.KategorieService;
import de.ziad.abschlussprojekt.service.UserService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AdminController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private AnzeigeService anzeigeService ;
    
    @Autowired
    private KategorieService kategorieService ;
    

    @GetMapping("/admin/welcome_admin")
    public String welcomeAdminAction(Model model) {
        
        return "welcome_admin";
    }
    
    
    @GetMapping("/admin/logout")
    public String logoutAction(Model model) {
        
        return "redirect:/index";
    }
    
    
    
    // User Actions :
    
    @GetMapping("/admin/utadmin")
    public String showUTAdminAction(Model model) {
        model.addAttribute("userList", userService.getAll());
        return "utadmin";
    }
    

    
    
    @GetMapping("admin/utadmin/edituser/{id}")
    public String editUser(@PathVariable(name = "id") Long id, Model model) {

       User user = userService.getById(id);
       model.addAttribute("user", user) ;
        return "edituser";
    }
    
    @PostMapping("/admin/utadmin/edituser")
    public String editUserAction(Model model, User user) { 
        userService.save(user) ;
        List<User> myUsers = userService.getAll() ;
        model.addAttribute("userList", myUsers);
        return "utadmin";
    }
    

    
    @RequestMapping(value = "/admin/utadmin/deleteuser/{id}" , method ={RequestMethod.GET,RequestMethod.DELETE})
    public String deleteUserAction(Model model, User user) { 
        userService.delete(user) ;
       
        return "redirect:/admin/utadmin";
    }
    
    
    // Anzeige Actions :
    @GetMapping("/admin/atadmin")
    public String showATAdminAction(Model model) {
        model.addAttribute("anzeigenList", anzeigeService.getAll());
        return "atadmin";
    }
    

    
    @GetMapping("admin/atadmin/editanzeige/{id}")
    public String editAnzeigeAction(@PathVariable(name = "id") Long id, Model model) {

       Anzeige anzeige = anzeigeService.getById(id).get();
       model.addAttribute("anzeige", anzeige) ;
       model.addAttribute("kategorienList", kategorieService.getHauptKategorie());
        
        return "editanzeige";
    }
    
    @PostMapping("/admin/atadmin/editanzeige")
    public String editAAction(@RequestParam(name = "erstellungsdatum") LocalDateTime erstellungsdatum, @RequestParam(name = "postalCode") String postalCode,@RequestParam(name = "anbieter.userName") String anbieteruserName, Model model, Anzeige anzeige) {
        System.out.println("what is your problem ?!");
     
     User user = userService.findByUserName(anbieteruserName) ;
     anzeige.setAnbieter(user);
      anzeige.setPostalCode(postalCode);
        anzeige.setErstellungsdatum(erstellungsdatum);
        anzeigeService.save(anzeige) ;
       
        List<Anzeige> myAnzeige = anzeigeService.getAll() ;
        model.addAttribute("anzeigenList", myAnzeige);
        return "redirect:/admin/atadmin" ;
    }
    
    @RequestMapping(value = "admin/atadmin/deleteanzeige/{id}", method ={RequestMethod.GET,RequestMethod.DELETE})
    public String deleteAnzeigeAction( Anzeige anzeige) {
       
  
        
        anzeigeService.delete(anzeige) ;
       
   
        return "redirect:/admin/atadmin";
    }
    
    
    // Kategorie Actions :
    
    @GetMapping("/admin/ktadmin")
    public String showKTAdminAction(Model model) {
        model.addAttribute("kategorieList", kategorieService.getAll());
        return "ktadmin";
    }
    
    @GetMapping("admin/ktadmin/addkategorie")
    public String addKategorieAction(Model model) {

       
       model.addAttribute("kategorie", new Kategorie()) ;
       model.addAttribute("kategorieList", kategorieService.getHauptKategorie());
      
    
        return "addkategorie";
    }
    
    @PostMapping("admin/ktadmin/addkategorie")
    public String addingKategorieAction(Kategorie kategorie , @RequestParam(value = "parent.id", required = false) Kategorie parent) {
      
       if(parent != null) {   
        kategorie.setParent(parent);
        kategorieService.save(kategorie) ;
       
     }else {
        kategorie.setParent(null);
        
        kategorieService.save(kategorie);
        
       }
    
    return "redirect:/admin/ktadmin" ;
    }
    
     @GetMapping("admin/ktadmin/editkategorie/{id}")
    public String editKategorieAction(@PathVariable(name = "id") Long id, Model model) {

     //  Kategorie kategorie = kategorieService.getById(id);
       model.addAttribute("kategorie", kategorieService.getById(id)) ;
        return "editkategorie";
    }
    
        @PostMapping("/admin/ktadmin/editkategorie")
    public String editKAction(Model model, Kategorie kategorie) {
       
        System.out.println(kategorie.toString());
        kategorieService.save(kategorie) ;
       
        List<Kategorie> myKategorie = kategorieService.getAll() ;
        model.addAttribute("kategorieList", myKategorie);
        return "redirect:/admin/ktadmin";
    }
    

    @RequestMapping(value = "admin/ktadmin/deletekategorie/{id}", method ={RequestMethod.GET,RequestMethod.DELETE})
    public String deleteKAction( Kategorie kategorie) {
        
        
       List<Kategorie> kChildren = kategorie.getChildren();
       for(Kategorie kat: kChildren) {
            kChildren.remove(kat);
       } 
        kategorieService.delete(kategorie) ;
       
        return "redirect:/admin/ktadmin";
    }
    
}
