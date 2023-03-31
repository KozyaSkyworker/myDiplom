package com.example.myDiplom.controllers;

import com.example.myDiplom.entities.Author;
import com.example.myDiplom.entities.AuthorTerm;
import com.example.myDiplom.entities.Term;
import com.example.myDiplom.repositories.AuthorRepository;
import com.example.myDiplom.repositories.AuthorTermRepository;
import com.example.myDiplom.repositories.TermRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
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

    // GET SEARCH

    @GetMapping("/search")
    public String getSearch(@RequestParam String name, Model model) {
        Term term = termRepository.findByName(name);

        if (term == null){
            List<Term> termList = termRepository.findByNameContains(name);
            for (Term t : termList){
                System.out.println("T ->, "+ t);
            }
            if(termList.size() == 0){
                model.addAttribute("name", name);
            }
            model.addAttribute("termList", termList);
            return "search";
        }

        model.addAttribute("term", term);

        try {
            Iterable<AuthorTerm> authorTerm = authorTermRepository.findAll();
            for(AuthorTerm at : authorTerm){

                if(at.getId_term().equals(term.getId_term())){

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

    @GetMapping("/search/extended")
    public String getExtendedSearch(){
        return "search";
    }

    // GET MODERATOR

    @GetMapping("/moderator")
    public String getModeratorPage(Model model) {

        Iterable<Term> terms = termRepository.findAll();
        Iterable<Author> authors = authorRepository.findAll();
        Iterable<AuthorTerm> authorTerms = authorTermRepository.findAll();

        model.addAttribute("terms", terms);
        model.addAttribute("authors", authors);
        model.addAttribute("authorTerms", authorTerms);

        return "moderatoroverview";
    }

    @GetMapping("/moderator/term/{id}")
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

    @GetMapping("/moderator/author/{id}")
    public String getAuthorById(@PathVariable("id") Integer id, Model model){
        Optional<Author> author = authorRepository.findById(id);
        model.addAttribute("author", author.get());
        return  "authorPage";
    }

    @GetMapping("/moderator/new")
    public String newTerm(@ModelAttribute("term") Term term, @ModelAttribute("author") Author author,
                          @ModelAttribute("authorTerm") AuthorTerm authorTerm){
        return "forms/new";
    }
    @GetMapping("/moderator/new/author")
    public String newAuthor(@ModelAttribute("author") Author author){
        return "forms/newAuthor";
    }

    @GetMapping("/moderator/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Term term = termRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid term Id:" + id));

        model.addAttribute("term", term);
        return "forms/updateTerm";
    }

    @GetMapping("/moderator/delete/term/{id}")
    public String deleteTerm(@PathVariable("id") Integer id) {
        termRepository.deleteById(id);
        return "redirect:/moderator";
    }

    @GetMapping("/moderator/delete/author/{id}")
    public String deleteAuthor(@PathVariable("id") Integer id) {
        authorRepository.deleteById(id);
        return "redirect:/moderator";
    }

    // POST MODERATOR

    @PostMapping("/moderator/new")
    public String submitNewInfo(@Valid @ModelAttribute Author author, BindingResult bindingResult1,
                                @Valid @ModelAttribute AuthorTerm authorTerm, BindingResult bindingResult2,
                                @Valid @ModelAttribute Term term, BindingResult bindingResult3) {
        System.out.println("post est");
        System.out.println("1");

        if (bindingResult1.hasErrors() || bindingResult2.hasErrors() || bindingResult3.hasErrors()) {

            return "forms/new";
        }
        System.out.println("2");

        termRepository.save(term);
        authorRepository.save(author);
        authorTermRepository.save(authorTerm);

        return "redirect:/moderator/new";
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

    @PostMapping("/moderator/update/{id}")
    public String updateTerm(@PathVariable("id") Integer id, @Valid Term term,
                             BindingResult result) {
        if (result.hasErrors()) {

            return "forms/updateTerm";
        }
        term.setId_term(id);
        termRepository.save(term);
        return "redirect:/moderator";
    }

    // GET AUTHORIZATION

    @GetMapping("/authorization")
    public String getAuthorizationPage(){
        return "authorization";
    }
}