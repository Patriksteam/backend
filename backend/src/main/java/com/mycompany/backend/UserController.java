package com.mycompany.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService userService;
    private final ShoppingListService shoppingListService;
    private final PasswordEncoder passwordEncoder;  // Füge den PasswordEncoder hinzu

    @Autowired
    public UserController(UserService userService, ShoppingListService shoppingListService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.shoppingListService = shoppingListService;
        this.passwordEncoder = passwordEncoder;  // Injektion des PasswordEncoders
    }

    @GetMapping("/login")
    public String showProfilePage() {
        return "login";  // Zeigt die Profilseite (Login/Registrierung)
    }

  

    @GetMapping("/userForm")
    public String showUserForm(Model model) {
        model.addAttribute("user", new User());
        return "userForm";  // Zeigt das Registrierungsformular
    }

    @GetMapping("/profil")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User()); // User ist dein Datenmodell
        return "profil";
    }

    @PostMapping("/addUser")
    public String addUser(@RequestParam String username, 
                          @RequestParam String email,
                          @RequestParam String password, 
                          @RequestParam(required = false) String nachname,
                          @RequestParam(required = false) String postleitzahl,
                          @RequestParam(required = false) String adresse,
                          @RequestParam(required = false) String hausnummer,
                          Model model) {
        // Erstelle einen neuen Benutzer und speichere ihn
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));  // Passwort direkt verschlüsseln
        user.setNachname(nachname);
        user.setPostleitzahl(postleitzahl);
        user.setAdresse(adresse);
        user.setHausnummer(hausnummer);

        userService.createUser(user);  // Benutzer speichern

        return "redirect:/";  // Weiterleitung zur Startseite oder Login-Seite
    }
}
