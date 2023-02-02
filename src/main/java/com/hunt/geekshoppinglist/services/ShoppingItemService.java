package com.hunt.geekshoppinglist.services;

import com.hunt.geekshoppinglist.model.ShoppingItem;
import com.hunt.geekshoppinglist.model.User;
import com.hunt.geekshoppinglist.repository.ShoppingItemRepository;
import com.hunt.geekshoppinglist.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class ShoppingItemService {

    private ShoppingItemRepository repository;

    private final UserRepo userRepo;
    @Autowired
    public ShoppingItemService(ShoppingItemRepository repository, UserRepo userRepo) {
        this.repository = repository;
        this.userRepo = userRepo;
    }

    public List<ShoppingItem>  getFindAll(Principal principal){
        return repository.findByUserUsername(principal.getName());
    }


    public void newShoppingItem(ShoppingItem item, Principal principal){
        User user = userRepo.findByUsername(principal.getName()).get();
        item.setUser(user);
        repository.save(item);
    }

    public void deleteShoppingItem(Long id){
        repository.deleteById(id);
    }
}
