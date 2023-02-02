package com.hunt.geekshoppinglist.controllers;

import com.hunt.geekshoppinglist.model.ShoppingItem;
import com.hunt.geekshoppinglist.services.ShoppingItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class MainController {
    private Logger logger = LoggerFactory.getLogger(MainController.class);
    private ShoppingItemService itemService;
    @Autowired
    public MainController(ShoppingItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/")
    public String indexPage(Model model, Principal principal){
        logger.info("User name {}", principal.getName());
        model.addAttribute("items", itemService.getFindAll(principal));
        model.addAttribute("item", new ShoppingItem());
        return "index";
    }

    @PostMapping("/")
    public String newShoppingItem(ShoppingItem item, Principal principal){
        if (item.getName().isEmpty()) {
            return "redirect:/";
        }
        itemService.newShoppingItem(item, principal);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String newShoppingItem(@PathVariable Long id){
        itemService.deleteShoppingItem(id);
        return "redirect:/";
    }






}
