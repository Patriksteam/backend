package com.mycompany.backend;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {
    List<ShoppingList> findByUser(User user);
}

