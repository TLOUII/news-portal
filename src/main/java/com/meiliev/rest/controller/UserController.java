package com.meiliev.rest.controller;

import com.meiliev.database.dao.UserDAO;
import com.meiliev.database.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserDAO userController;

    @Autowired
    public UserController(UserDAO userController) {
        this.userController = userController;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", userController.getListUsers());
        return "user/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("person", userController.findUserById(id));
        return "user/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") User User) {
        return "user/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") User person) {
        userController.createUser(person);
        return "redirect:/user";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("person", userController.findUserById(id));
        return "user/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") User user, @PathVariable("id") Long id) {
        userController.update(id, user);
        return "redirect:/user";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userController.deleteUser(id);
        return "redirect:/user";
    }
}
