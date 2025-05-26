
package com.mycompany.backend;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginRedirectController {

    @GetMapping("/login")
    public void redirectIfAuthenticated(HttpServletResponse response) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean isLoggedIn = auth != null && auth.isAuthenticated()
                && !auth.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_ANONYMOUS"));

        if (isLoggedIn) {
            response.sendRedirect("/profil");
        } else {
            response.sendRedirect("/login.html"); // oder deine Login-HTML
        }
    }
}

