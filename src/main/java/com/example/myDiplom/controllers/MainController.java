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

    @GetMapping("/moderator")
    public String getModeratorPage(Model model) {

        Iterable<Term> terms = termRepository.findAll();

        model.addAttribute("term", terms);


        return "moderatoroverview";
    }

    @GetMapping("/moderator/new/term")
    public String newTerm(@ModelAttribute("term") Term term){
        return "forms/newTerm";
    }
    @GetMapping("/moderator/new/author")
    public String newAuthor(Model model){
        model.addAttribute("author", new Author());
        return "forms/newAuthor";
    }
    @GetMapping("/moderator/new/authorterm")
    public String newAuthorTerm(Model model){

        model.addAttribute("authorTerm", new AuthorTerm());
        return "forms/newAuthorTerm";
    }

    // POST MODERATOR

//    @RequestMapping(value = "/moderator", method = RequestMethod.POST, params = "term")
//    public String submitTerm(@ModelAttribute("term") Term term,
//                              BindingResult bindingResult, final ModelMap modelMap) {
//
//
//        termRepository.save(term);
//        return "redirect:/moderator";
//    }
//
//    @RequestMapping(value = "/moderator", method = RequestMethod.POST, params = "author")
//    public String submitAuthor(@ModelAttribute("author") @Valid Author author,
//                              BindingResult bindingResult, final ModelMap modelMap) {
//
//
//        authorRepository.save(author);
//        return "redirect:/moderator";
//    }
//
//    @RequestMapping(value = "/moderator", method = RequestMethod.POST, params = "authorTerm")
//    public String submitAuthorTerm(@ModelAttribute("authorTerm") @Valid AuthorTerm authorTerm,
//                              BindingResult bindingResult, final ModelMap modelMap) {
//
//
//        authorTermRepository.save(authorTerm);
//        return "redirect:/moderator";
//    }

    // GET AUTHORIZATION

    @GetMapping("/authorization")
    public String getAuthorizationPage(){
        return "authorization";
    }
}


