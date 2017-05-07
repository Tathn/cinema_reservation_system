package com.cinema.web;

import com.cinema.domain.User;
import com.cinema.domain.UserRepository;
import com.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;

/**
 * Created by Patryk on 2017-04-19.
 */
@Controller
@RequestMapping
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserRepository userRepository){
        userService = new UserService(userRepository);
    }

    @GetMapping("/users")
    public String get(Model model){
        Collection<User> users = userService.findAll();
        model.addAttribute("users",users);
        return "users/all";
    }



    @GetMapping("/user/edit/{userId}")
    public String initEditForm(@PathVariable Long userId, Model model){

        model.addAttribute("user", userService.findById(userId));
        return "users/edit";
    }

    @PostMapping("/user/edit/{userId}")
    public String processEditForm(@PathVariable Long userId, @ModelAttribute User user, BindingResult result, RedirectAttributes redir){
        if (result.hasErrors()) {
            return "users/edit";
        } else {
            userService.save(user);
            redir.addFlashAttribute("message","User edited!");
            return "redirect:/users/";
        }
    }

    @GetMapping("/user/delete/{userId}")
    public String removeCustomer(@PathVariable Long userId, RedirectAttributes redir){
        userService.delete(userId);
        redir.addFlashAttribute("message","User deleted!");
        return "redirect:/users/";
    }
}
