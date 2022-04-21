
package de.ziad.abschlussprojekt.controller;

import de.ziad.abschlussprojekt.model.Anzeige;
import de.ziad.abschlussprojekt.service.AnzeigeService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author User
 */
@Controller
public class MainController {
    
    @Autowired
    AnzeigeService anzeigeService ;
    
    
    @RequestMapping("/")
    public String page(Model model) {
        model.addAttribute("attribute", "value");
        return "index";
    }
    @RequestMapping("/home")
    public String homePage(Model model) {
        
       
        model.addAttribute("anzeigenList", anzeigeService.getAll());
        return "home";
    }
    
    @GetMapping("/home/homeanzeigedetails/{id}")
    public String showAnzeigeDetails(@PathVariable("id") Long id, Model model) {
        Optional<Anzeige> opt = anzeigeService.getById(id);
        if(opt.isPresent()) {
            model.addAttribute("anzeige", opt.get());
            return "homeanzeigedetails";
        }
        return "error";
       
    }
    
 }
