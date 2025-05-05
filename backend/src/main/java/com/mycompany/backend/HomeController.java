    package com.mycompany.backend;

    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";  // Zeigt die Startseite (home.html)
    }

    @GetMapping("/home")
    public String showHomePage() {
        return "home";  // Zeigt ebenfalls die Startseite (home.html), falls /home aufgerufen wird
    }
}


