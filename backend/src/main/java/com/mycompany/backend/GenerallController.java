package com.mycompany.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GenerallController {

    @GetMapping("/support")
    public String support() {
        return "support"; // Zeigt support.html
    }

    @GetMapping("/agbs")
    public String agbs() {
        return "agbs"; // Zeigt agbs.html
    }

    @GetMapping("/partner")
    public String partner() {
        return "partner"; // Zeigt partner.html
    }

    @GetMapping("/ueber_uns")
    public String ueberUns() {
        return "ueber_uns"; // Zeigt ueber_uns.html
    }

    @GetMapping("/admin_passwort")
    public String adminPasswort() {
        return "admin_passwort"; // Zeigt admin_passwort.html
    }
}

