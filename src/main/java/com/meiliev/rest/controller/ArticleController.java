package com.meiliev.rest.controller;

import com.meiliev.database.dao.ArticleDAO;
import com.meiliev.database.entity.Article;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/article")
public class ArticleController {

    private final ArticleDAO articleDAOControl;

    public ArticleController(ArticleDAO articleDAOControl) {
        this.articleDAOControl = articleDAOControl;
    }

    @GetMapping()
    public String returnAllArticlesOnPage(Model model) {
        model.addAttribute("articlesList", articleDAOControl.findAll());
        return "article/allArticles";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("necessaryTitle", articleDAOControl.findById(id));
        return "article/oneArticle";
    }

    @GetMapping("/new")
    public String createNewArticle(Model model) {
        model.addAttribute("article", new Article());
        return "article/createNewArticle";
    }

    @PostMapping()
    public String create(@ModelAttribute("article") Article article) {
        articleDAOControl.create(article);
        return "redirect:/article";
    }

    @GetMapping("/{id}/edit")
    public String editArticle(Model model, @PathVariable("id") Long id) {
        model.addAttribute("editArticle", articleDAOControl.findById(id));
        return "article/editArticle";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("articl") Article article, @PathVariable("id") Long id) {
        articleDAOControl.update(id, article);
        return "redirect:/article";
    }

    @DeleteMapping("/{id}")
    public String deleteArticle(@PathVariable("id") Long id) {
        articleDAOControl.delete(id);
        return "redirect:/article";
    }
}
