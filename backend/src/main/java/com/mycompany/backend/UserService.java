package com.mycompany.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Methode zum Abrufen eines Benutzers anhand der E-Mail-Adresse
    public User getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.orElse(null);  // Gibt den Benutzer zurück oder null, wenn nicht gefunden
    }

    // Die Methode speichert den Benutzer in der Datenbank
    public void createUser(User user) {
        userRepository.save(user);
    }

    // Die Methode ruft alle Benutzer aus der Datenbank ab
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Weitere Methoden für andere Felder können hier hinzugefügt werden
}
