package com.mycompany.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;

@Controller
public class UserController {

    private final UserService userService;
    private final ShoppingListService shoppingListService;

    @Autowired
    public UserController(UserService userService, ShoppingListService shoppingListService) {
        this.userService = userService;
        this.shoppingListService = shoppingListService;
    }

    @GetMapping("/Profil")
    public String showProfilePage() {
        return "Profil";  // Zeigt die Profilseite (Login/Registrierung)
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String email, 
                            @RequestParam String password, 
                            Model model) {
        // Hier kannst du die Login-Logik implementieren, z.B. Benutzerprüfung und Passwortverifizierung
        User user = userService.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            model.addAttribute("user", user);
            return "redirect:/user/home";  // Weiterleitung zur Benutzer-Startseite
        } else {
            model.addAttribute("error", "Ungültige E-Mail oder Passwort!");
            return "profil";  // Zurück zur Login-Seite mit Fehlermeldung
        }
    }

        @GetMapping("/userForm")
        public String showUserForm(Model model) {
            model.addAttribute("user", new User());
            return "userForm";  // Zeigt das Registrierungsformular
        }
        @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User()); // User ist dein Datenmodell
        return "Login";
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
        user.setPassword(password);  // Passwörter sollten verschlüsselt gespeichert werden (z.B. mit BCrypt)
        user.setNachname(nachname);
        user.setPostleitzahl(postleitzahl);
        user.setAdresse(adresse);
        user.setHausnummer(hausnummer);

        userService.createUser(user);  // Benutzer speichern

        return "redirect:/";  // Weiterleitung zur Startseite oder Login-Seite
    }
}
