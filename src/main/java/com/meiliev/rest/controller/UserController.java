package com.meiliev.rest.controller;

import com.meiliev.database.entity.User;
import com.meiliev.database.Dao.UserDAOControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserDAOControl userController;

    @Autowired
    public UserController(UserDAOControl userController) {
        this.userController = userController;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", userController.index());
        return "user/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("person", userController.show(id));
        return "user/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") User User) {
        return "user/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") User person) {
        userController.save(person);
        return "redirect:/user";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("person", userController.show(id));
        return "user/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") User user, @PathVariable("id") Long id) {
        userController.updatePass(id, user);
        return "redirect:/user";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userController.delete(id);
        return "redirect:/user";
    }
}
