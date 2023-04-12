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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Optional;

@Controller
public class ModeratorController {

    @Autowired
    private TermRepository termRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorTermRepository authorTermRepository;

    // GET MODERATOR

    @GetMapping("/moderator")
    public String getModeratorPage(@RequestParam(required = false) String isTerm,
                                   @RequestParam(required = false) String isAuthor,
                                   @RequestParam(required = false) String name,
                                   Model model) {
        try {
            try {
                if (isTerm.equals("on")){
                    Iterable<Term> terms = termRepository.findAll();

                    model.addAttribute("terms", terms);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if (isAuthor.equals("on")) {
                    Iterable<Author> authors = authorRepository.findAll();

                    model.addAttribute("authors", authors);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "moderatoroverview";
        }
        catch (NullPointerException e){
            System.out.println(e);
            return "moderatoroverview";
        }

    }

    // TERM PAGE

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
        model.addAttribute("moderator", true);
        return "detailed/termPage";
    }

    // AUTHOR PAGE

    @GetMapping("/moderator/author/{id}")
    public String getAuthorById(@PathVariable("id") Integer id, Model model){
        Optional<Author> author = authorRepository.findById(id);
        model.addAttribute("author", author.get());
        model.addAttribute("moderator", true);
        return  "detailed/authorPage";
    }

    // NEW INFO

    @GetMapping("/moderator/new")
    public String newTerm(@ModelAttribute("term") Term term,
                          @ModelAttribute("author") Author author,
                          @ModelAttribute("authorTerm") AuthorTerm authorTerm,
                          Model model){
        Iterable<Author> authors = authorRepository.findAll();
        model.addAttribute("authors", authors);
        return "forms/new";
    }
    @GetMapping("/moderator/new/author")
    public String newAuthor(@ModelAttribute("author") Author author){
        return "forms/newAuthor";
    }

    // EDIT

    @GetMapping("/moderator/edit/term/{id}")
    public String showUpdateTermForm(@PathVariable("id") Integer id, Model model) {
        Term term = termRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid term Id:" + id));

        model.addAttribute("term", term);
        return "forms/updateTerm";
    }
    @GetMapping("/moderator/edit/author/{id}")
    public String showUpdateAuthorForm(@PathVariable("id") Integer id, Model model) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid author Id:" + id));

        model.addAttribute("author", author);
        return "forms/updateAuthor";
    }

    // DELETE

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

    // AUTHORS COMPARE PAGE

    @GetMapping("/moderator/compare")
    public String compareAuthors(@RequestParam(required = false) Integer leftAuthorId,
                                 @RequestParam(required = false) Integer rightAuthorId,
                                 Model model) {

        Iterable<Author> authors = authorRepository.findAll();
        model.addAttribute("authors", authors);

        if(leftAuthorId != null && rightAuthorId != null){
            if(leftAuthorId != 0 && rightAuthorId != 0){
                Optional<Author> leftAuthor = authorRepository.findById(leftAuthorId);
                Optional<Author> rightAuthor = authorRepository.findById(rightAuthorId);

                model.addAttribute("leftAuthor", leftAuthor.get());
                model.addAttribute("rightAuthor", rightAuthor.get());
            }
        }

        return "compareAuthors";
    }

    // POST MODERATOR

    @PostMapping("/moderator/new")
    public String submitNewInfo(@ModelAttribute Author author,
                                @Valid @ModelAttribute AuthorTerm authorTerm, BindingResult bindingResult2,
                                @Valid @ModelAttribute Term term, BindingResult bindingResult3,
                                Model model,
                                @RequestParam Integer authorID) {

        if (bindingResult2.hasErrors() || bindingResult3.hasErrors() || authorID == 0) {
            Iterable<Author> authors = authorRepository.findAll();
            model.addAttribute("authors", authors);
            model.addAttribute("undefined", true);
            return "forms/new";
        }

        termRepository.save(term);
        authorTerm.setId_term(term.getId_term());
        authorTerm.setId_author(authorID);
        authorTermRepository.save(authorTerm);

        model.addAttribute("success", true);

        return "forms/new";
    }
    @PostMapping("/moderator/new/author")
    public String submitAuthor(@Valid Author author, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "forms/newAuthor";
        }

        model.addAttribute("author", author);

        authorRepository.save(author);

        model.addAttribute("success", true);

        return "forms/newAuthor";
    }

    // UPDATE

    @PostMapping("/moderator/update/term/{id}")
    public String updateTerm(@PathVariable("id") Integer id, @Valid Term term,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "forms/updateTerm";
        }
        term.setId_term(id);
        termRepository.save(term);

        model.addAttribute("success", true);

        return "forms/updateTerm";
    }
    @PostMapping("/moderator/update/author/{id}")
    public String updateAuthor(@PathVariable("id") Integer id, @Valid Author author,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {

            return "forms/updateAuthor";
        }
        author.setId_author(id);
        authorRepository.save(author);

        model.addAttribute("success", true);

        return "forms/updateAuthor";
    }

}
