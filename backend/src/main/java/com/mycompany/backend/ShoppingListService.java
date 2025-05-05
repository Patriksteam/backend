package com.mycompany.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

import java.util.List;

@Service
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;

    @Autowired
    public ShoppingListService(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }
    

    // Einkaufslisten für einen bestimmten Benutzer abrufen
   public List<ShoppingList> getShoppingListForUser(User user) {
    List<ShoppingList> list = shoppingListRepository.findByUser(user);
    if (list == null) {
        return new ArrayList<>();
    }
    return list;
}

    // Artikel zur Einkaufsliste eines Benutzers hinzufügen
    public void addItemToShoppingList(User user, String item) {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setItem(item);
        shoppingList.setUser(user);
        shoppingListRepository.save(shoppingList);
    }
    public void createEmptyListForUser(User user) {
    // optional, je nach deinem Datenmodell
}

}

