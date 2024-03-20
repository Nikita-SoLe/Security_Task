package ru.itmentor.spring.boot_security.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("/admin")
public class CrudController {
    private final UserService service;

    public CrudController(UserService service) {
        this.service = service;
    }

    @GetMapping("")
    public String showAllUsers(Model model) {
        model.addAttribute("allUsers", service.getList());
        return "show-all-users";
    }

    @GetMapping("/askDetails")
    public String askUserDetails(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", service.getRoles());
        return "ask-user-details-view";
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute("user") User user,
                             @RequestParam("role") String[] selectedRoles) {
        service.create(user, selectedRoles);
        return "redirect:/admin";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@ModelAttribute("user") User user) {
        service.delete(user);
        return "redirect:/admin";
    }

    @RequestMapping("/viewUser")
    public String viewUser(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", service.read(id));
        model.addAttribute("allRoles", service.getRoles());
        return "show-user-details-view";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam("role") String[] selectedRoles) {
        service.update(user, selectedRoles);
        return "redirect:/admin";
    }
}
