package ro.utcn.wasteless.service;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.utcn.wasteless.domain.GroceryItem;
import ro.utcn.wasteless.domain.User;
import ro.utcn.wasteless.repository.GroceryRepository;
import ro.utcn.wasteless.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WastelessServiceImpl implements WastelessService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroceryRepository groceryRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(String username, String password) {
        Optional<User> user =  Optional.empty();
        List<User> foundUsers = userRepository.findByUsernameAndPassword(username, password);
        if (foundUsers.size() != 0){
            user = Optional.of(foundUsers.get(0));
        }
        return user;
    }

    @Override
    public GroceryItem addGrocery(GroceryItem groceryItem, User user) {
        groceryItem.setUser(user);
        return groceryRepository.save(groceryItem);
    }


    @Override
    public List<GroceryItem> getItemsByUser(User user) {
        List<GroceryItem> groceryItems = groceryRepository.findAll().stream()
                .filter(item -> item.getUser().getId().equals(user.getId())).collect(Collectors.toList());
        return groceryItems;
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user;
    }

    @Override
    @Transactional
    public Optional<GroceryItem> deleteItem(Long itemId) {
        Optional<GroceryItem> item = groceryRepository.findById(itemId);
        if (item.isEmpty()){
            return Optional.empty();
        }
        groceryRepository.delete(item.get());
        return item;
    }




}
