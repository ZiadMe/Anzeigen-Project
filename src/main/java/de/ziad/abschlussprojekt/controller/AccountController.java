package de.ziad.abschlussprojekt.controller;

import de.ziad.abschlussprojekt.model.Anzeige;
import de.ziad.abschlussprojekt.model.Kategorie;
import de.ziad.abschlussprojekt.model.User;
import de.ziad.abschlussprojekt.repository.KategorieRepository;
import de.ziad.abschlussprojekt.repository.UserRepository;
import de.ziad.abschlussprojekt.service.AnzeigeService;
import de.ziad.abschlussprojekt.service.KategorieService;
import de.ziad.abschlussprojekt.userdto.UserDto;
import de.ziad.abschlussprojekt.service.UserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

public class AccountController {
    
    @Autowired
    private UserService userservice;
    
    
    
    @GetMapping("/login")
    public String loginAction(Model model) {
        
        return "login";
    }
    @GetMapping("/login-error")
    public String loginErrorAction(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
    
  //  @PostMapping("/logout")
    @GetMapping("/logout")
    public String logoutAction(Model model) {
        return "redirect:/";
    }
    
    @GetMapping("/registration")
    public String userFormAction(Model model) {
        model.addAttribute("user", new UserDto());
        return "registration";
    }
    
    @PostMapping("/registration")
    public String addUserAction(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model) {
        if(result.hasErrors()) {
            System.out.println(result);
            return "registration";
        }
        
        userservice.createUser(userDto);
        return "redirect:/registration?success";
    }
    
    
}
