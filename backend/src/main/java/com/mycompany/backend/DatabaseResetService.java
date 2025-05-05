package com.mycompany.backend;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class DatabaseResetService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void resetDatabase() {
        try {
            System.out.println("Starte das Zurücksetzen der Datenbank...");

            // Deaktivieren der Fremdschlüsselüberprüfung
            entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
            System.out.println("Fremdschlüsselprüfung deaktiviert.");

            // Tabellen zurücksetzen
            entityManager.createNativeQuery("TRUNCATE TABLE app_users").executeUpdate();
            System.out.println("Tabelle app_user zurückgesetzt.");

            entityManager.createNativeQuery("TRUNCATE TABLE shopping_list").executeUpdate();
            System.out.println("Tabelle shopping_list zurückgesetzt.");

            // Fremdschlüsselprüfung wieder aktivieren
            entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
            System.out.println("Fremdschlüsselprüfung aktiviert.");

        } catch (Exception e) {
            System.err.println("Fehler beim Zurücksetzen der Datenbank: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
