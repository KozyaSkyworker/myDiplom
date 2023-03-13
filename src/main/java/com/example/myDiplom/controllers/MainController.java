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

import java.io.IOException;
import java.util.Optional;

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

        model.addAttribute("terms", terms);

        return "moderatoroverview";
    }

    @GetMapping("/moderator/{id}")
    public String getTermById(@PathVariable("id") Integer id, Model model){
        Optional<Term> term = termRepository.findById(id);
        model.addAttribute("term", term.get());

        try {
            Iterable<AuthorTerm> authorTerm = authorTermRepository.findAll();
            for(AuthorTerm at : authorTerm){

                if(at.getId_term().equals(term.get().getId_term())){

                    Integer authorId = at.getId_author();

                    Optional<Author> author = authorRepository.findById(authorId);

                    model.addAttribute("author", author.get());
                    model.addAttribute("authorTerm", at);
                }
            }

        }
        finally {

        }


        return "termPage";
    }

    @GetMapping("/moderator/new/term")
    public String newTerm(@ModelAttribute("term") Term term){
        return "forms/newTerm";
    }
    @GetMapping("/moderator/new/author")
    public String newAuthor(@ModelAttribute("author") Author author){
        return "forms/newAuthor";
    }
    @GetMapping("/moderator/new/authorterm")
    public String newAuthorTerm(@ModelAttribute("authorTerm") AuthorTerm authorTerm){
        return "forms/newAuthorTerm";
    }

    // POST MODERATOR

    @PostMapping("/moderator/new/term")
    public String submitTerm(@Valid Term term, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "forms/newTerm";
        }

        model.addAttribute("term", term);

        termRepository.save(term);

        return "redirect:/moderator/new/term";
    }
    @PostMapping("/moderator/new/author")
    public String submitAuthor(@Valid Author author, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "forms/newAuthor";
        }

        model.addAttribute("author", author);

        authorRepository.save(author);

        return "redirect:/moderator/new/author";
    }
    @PostMapping("/moderator/new/authorterm")
    public String submitAuthorTerm(@Valid AuthorTerm authorTerm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "forms/newAuthorTerm";
        }

        model.addAttribute("authorTerm", authorTerm);

        authorTermRepository.save(authorTerm);

        return "redirect:/moderator/new/authorterm";
    }

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


