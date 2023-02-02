package com.hunt.geekshoppinglist.controllers;

import com.hunt.geekshoppinglist.repository.UserRepo;
import com.hunt.geekshoppinglist.services.UserDTO;
import com.hunt.geekshoppinglist.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private final UserService userService;
@Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }


    @GetMapping("/register")
    public String registerPage(Model model){
        model.addAttribute("user", new UserDTO());
        return "register";
    }


    /*биндинг для проверки и валидации данных, о том, что за пользователь добавляется
    * Впереди перед дто добавляется валуе, он проверяет на соотвествие аннотация указанным в классе дто
    * например нотбланк*/
    @PostMapping("/register")
    public String registerNewUser(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult bindingResult){
        /*Проверяем произошли ли какие-нибудь ошибки в процессе валидации биндингом*/
        if (bindingResult.hasErrors()){
            return "register";
        }
        /*Проверяем на совпадение паролей*/
        if (!userDTO.getPassword().equals(userDTO.getRepeatPassword())){
            bindingResult.rejectValue("password", "", "Пароли не совпадают");
            return "register";
        }
        userService.create(userDTO);
        return "redirect:/login";
    }
}
