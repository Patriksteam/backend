package com.mycompany.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller; // NICHT RestController!
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private DatabaseResetService databaseResetService;

    @PostMapping("/reset-db")
    @ResponseBody
    public ResponseEntity<String> resetDatabase() {
        databaseResetService.resetDatabase();
        return ResponseEntity.ok("Datenbank wurde zurückgesetzt.");
    }

    @GetMapping
    public String showAdminPage() {
        return "admin"; // Erwartet admin.html im templates-Ordner
    }
}

