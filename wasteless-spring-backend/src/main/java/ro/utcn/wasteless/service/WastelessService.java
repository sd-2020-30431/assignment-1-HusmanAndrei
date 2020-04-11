package ro.utcn.wasteless.service;

import org.springframework.stereotype.Service;
import ro.utcn.wasteless.domain.GroceryItem;
import ro.utcn.wasteless.domain.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface WastelessService {

    User saveUser(User user);
    Optional<User> getUser(String username, String password);
    GroceryItem addGrocery(GroceryItem groceryItem, User user);
    List<GroceryItem> getItemsByUser(User user);
    Optional<User> getUserById(Long userId);
    Optional<GroceryItem> deleteItem(Long itemId);


}
