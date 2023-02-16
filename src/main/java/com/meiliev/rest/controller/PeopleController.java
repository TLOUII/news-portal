//package com.meiliev.rest.controller;
//
//import com.meiliev.database.entity.User;
//import com.meiliev.database.repository.UserDao.UserDAO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/people")
//public class PeopleController {
//
//    private final UserDAO userDAO;
//
//    @Autowired
//    public PeopleController(UserDAO userDAO) {
//        this.userDAO = userDAO;
//    }
//
//    @GetMapping()
//    public String index(Model model) {
//        model.addAttribute("people", userDAO.index());
//        return "people/index";
//    } // Первая страница
//
//    @GetMapping("/{id}")
//    public String show(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("person", userDAO.show(id));
//        return "people/show";
//    }// Вывод персональной страницы
//
//    @GetMapping("/new")
//    public String newPerson(@ModelAttribute("person") User User) {
//        return "people/new";
//    } // Добавление нового пользователя
//
//    @PostMapping()
////    TODO Переделать так чтоб-бы он брал последнйи
////     id и сам его вставлял в
////     новго пользователя с параметрами полученными из вьюшки
//    public String create(@ModelAttribute("person") User person) {
//        userDAO.save(person);
//        return "redirect:/people";
//    } // СОхранение нового пользователя в БД
//
//    @GetMapping("/{id}/edit")
//    public String edit(Model model, @PathVariable("id") Long id) {
//        model.addAttribute("person", userDAO.show(id));
//        return "people/edit";
//    } // Изменение пользователя в
//
//    @PatchMapping("/{id}")
//    public String update(@ModelAttribute("person") User user, @PathVariable("id") Long id) {
//        userDAO.updatePass(id, user);
//        return "redirect:/people";
//    } // Получение патчмаппинга с измененными данными
//
//    @DeleteMapping("/{id}")
//    public String delete(@PathVariable("id") Long id) {
//        userDAO.delete(id);
//        return "redirect:/people";
//    } // Удаление Пользователя
//}
