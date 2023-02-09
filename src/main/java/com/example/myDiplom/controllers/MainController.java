package com.example.myDiplom.controllers;

import com.example.myDiplom.entities.Author;
import com.example.myDiplom.entities.AuthorTerm;
import com.example.myDiplom.entities.Term;
import com.example.myDiplom.repositories.AuthorRepository;
import com.example.myDiplom.repositories.AuthorTermRepository;
import com.example.myDiplom.repositories.TermRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;


@Controller
public class MainController {
    @Autowired
    private TermRepository termRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorTermRepository authorTermRepository;

    // простой строчный вывод

    @GetMapping(path="/allterms")
    public @ResponseBody Iterable<Term> getAllTerms() {
        return termRepository.findAll();
    }

    @GetMapping(path="/allauthors")
    public @ResponseBody Iterable<Author> getAllAuthors() {return authorRepository.findAll();}

    @GetMapping(path="/allauthorterms")
    public @ResponseBody Iterable<AuthorTerm> getAllAuthorTerm() {
        return authorTermRepository.findAll();
    }

    // GET MODERATOR

//    @GetMapping("/moderator")
//    public String greeting(Model model) {
//
//        Iterable<Author> authors = authorRepository.findAll();
//        Iterable<Term> terms = termRepository.findAll();
//        Iterable<AuthorTerm> authorTerms = authorTermRepository.findAll();
//
//        model.addAttribute("authors", authors);
//        model.addAttribute("terms", terms);
//        model.addAttribute("authorTerm", authorTerms);
//
//        return "moderator";
//    }

    @GetMapping("/moderator")
    public String getModeratorPage(Model model) {
        model.addAttribute("term", new Term());
        model.addAttribute("author", new Author());
        model.addAttribute("authorTerm", new AuthorTerm());

        return "moderatoroverview";
    }

    // POST MODERATOR

    @RequestMapping(value = "/moderator", method = RequestMethod.POST, params = "term")
    public String submitTerm(@ModelAttribute("term") Term term,
                              BindingResult bindingResult, final ModelMap modelMap) {


        termRepository.save(term);
        return "redirect:/moderator";
    }

    @RequestMapping(value = "/moderator", method = RequestMethod.POST, params = "author")
    public String submitAuthor(@ModelAttribute("author") @Valid Author author,
                              BindingResult bindingResult, final ModelMap modelMap) {


        authorRepository.save(author);
        return "redirect:/moderator";
    }

    @RequestMapping(value = "/moderator", method = RequestMethod.POST, params = "authorTerm")
    public String submitAuthorTerm(@ModelAttribute("authorTerm") @Valid AuthorTerm authorTerm,
                              BindingResult bindingResult, final ModelMap modelMap) {


        authorTermRepository.save(authorTerm);
        return "redirect:/moderator";
    }

    // GET AUTHORIZATION

    @GetMapping("/authorization")
    public String getAuthorizationPage(){return "authorization";}

//    @PostMapping("/moderator")
//    public String addNew(@ModelAttribute Author author, Model model){
//        model.addAttribute("author", author);
//
//        authorRepository.save(author);
//
//        return "result";
//    }
}
