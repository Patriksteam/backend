
package com.mycompany.backend;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginRedirectController {

    @GetMapping("/login")
    public String redirectIfAuthenticated() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated() && 
            auth.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ROLE_ANONYMOUS"))) {
            // Eingeloggt -> weiterleiten zum Profil
            return "redirect:/profil";
        }

        // Nicht eingeloggt -> Login-Seite anzeigen
        return "login";
    }
}


