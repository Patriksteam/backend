package com.mycompany.backend;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    
    // Abruf eines Benutzers über die E-Mail-Adresse
    Optional<User> findByEmail(String email);
    
    // Abruf eines Benutzers über den Benutzernamen
    Optional<User> findByUsername(String username);
    
    // Abruf eines Benutzers über den Nachnamen
    Optional<User> findByNachname(String nachname);
    
    // Abruf eines Benutzers über die Postleitzahl
    Optional<User> findByPostleitzahl(String postleitzahl);
    
    // Abruf eines Benutzers über die Adresse
    Optional<User> findByAdresse(String adresse);
    
    // Abruf eines Benutzers über die Hausnummer
    Optional<User> findByHausnummer(String hausnummer);
    
    // Abruf eines Benutzers über das Passwort
    Optional<User> findByPassword(String password);
}



