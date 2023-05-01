package com.example.myDiplom.controllers;

import com.example.myDiplom.entities.Author;
import com.example.myDiplom.entities.AuthorTerm;
import com.example.myDiplom.entities.Term;
import com.example.myDiplom.repositories.AuthorRepository;
import com.example.myDiplom.repositories.AuthorTermRepository;
import com.example.myDiplom.repositories.TermRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

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
        if (name != null){
            model.addAttribute("request", true);

            if (isTerm != null){
                List<Term> termList = termRepository.findByNameContains(name);
                if(termList.size() == 0){
                    model.addAttribute("empty", name);
                }
                else {
                    model.addAttribute("termList", termList);
                }
            }

           if (isAuthor != null){
               List<Author> authorList = authorRepository.findByFullnameContains(name);
               if(authorList.size() == 0){
                   model.addAttribute("empty", name);
               }
               else {
                   model.addAttribute("authorList", authorList);
               }
           }
        }
        return "moderatoroverview";
    }

    // TERM PAGE

    @GetMapping("/moderator/term/{id}")
    public String getTermById(@PathVariable("id") Integer id, Model model){
        Optional<Term> term = termRepository.findById(id);
        model.addAttribute("term", term.get());

        try {
            List<AuthorTerm> authorTerms = authorTermRepository.findByTermId(id);
            List<Author> termAuthors = new ArrayList<>();
            for (AuthorTerm at : authorTerms){
                termAuthors.add(authorRepository.findById(at.getId_author()).get());
            }
            model.addAttribute("authorTerms", authorTerms);
            model.addAttribute("termAuthors", termAuthors);
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
    @GetMapping("/moderator/lists/delete/term/{id}")
    public String deleteTermFormLists(@PathVariable("id") Integer id){
        termRepository.deleteById(id);
        return "redirect:/moderator/lists";
    }
    @GetMapping("/moderator/lists/delete/author/{id}")
    public String deleteAuthorFormLists(@PathVariable("id") Integer id){
        authorRepository.deleteById(id);
        return "redirect:/moderator/lists";
    }
    @GetMapping("/moderator/lists/delete/authorterm")
    public String deleteAuthorTermFormLists(@RequestParam Integer idAuthor,
                                            @RequestParam Integer idTerm){
        System.out.println(idAuthor+" - "+idTerm);
        System.out.println();
        authorTermRepository.deleteAuthorTermByAuthorIdAndTermId(idAuthor, idTerm);
        return "redirect:/moderator/lists";
    }

    // CORRECT

    @GetMapping("/moderator/correct")
    public String getCorrectPage(@ModelAttribute("term") Term term,
                                 @ModelAttribute("author") Author author,
                                 @ModelAttribute("authorTerm") AuthorTerm authorTerm,
                                 Model model){
        Iterable<Term> terms = termRepository.findAll();
        Iterable<Author> authors = authorRepository.findAll();

        model.addAttribute("terms", terms);
        model.addAttribute("authors", authors);

        return "correct";
    }

    // LIST PAGE

    @GetMapping("/moderator/lists")
    public String getListsPage(Model model, @RequestParam(required = false) String params){
        try {
            if (params.equals("terms")){
                Iterable<Term> terms = termRepository.findAll();
                model.addAttribute("terms", terms);
            }
            else if (params.equals("authors")) {
                Iterable<Author> authors = authorRepository.findAll();
                model.addAttribute("authors", authors);
            }
            else {
                Iterable<AuthorTerm> authorTerms = authorTermRepository.findAll();
                model.addAttribute("authorTerms", authorTerms);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("moderator", true);

        return "lists";
    }

    // POST MODERATOR

    @PostMapping("/moderator/new")
    public String submitNewInfo(@ModelAttribute Author author,
                                @Valid @ModelAttribute AuthorTerm authorTerm, BindingResult bindingResult2,
                                @Valid @ModelAttribute Term term, BindingResult bindingResult3,
                                Model model,
                                @RequestParam List<Integer> authorID) {

        Iterable<Author> authors = authorRepository.findAll();

        if (bindingResult2.hasErrors() || bindingResult3.hasErrors()) {
            model.addAttribute("authors", authors);
            return "forms/new";
        }

        termRepository.save(term);

        String[] vklads = authorTerm.getAuthor_vklad().split(",");

        for (int i = 0; i < authorID.size(); i++){
            authorTerm.setId_term(term.getId_term());
            authorTerm.setId_author(authorID.get(i));
            authorTerm.setAuthor_vklad(vklads[i]);
            authorTermRepository.save(authorTerm);
        }

        model.addAttribute("authors", authors);
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

    // CORRECT

    @PostMapping("/moderator/correct")
    public String correctPageSubmit(@ModelAttribute("term") Term term,
                                 @ModelAttribute("author") Author author,
                                 @ModelAttribute("authorTerm") AuthorTerm authorTerm,
                                 Model model,
                                 @RequestParam(required = false) Integer termId,
                                 @RequestParam(required = false) Integer authorId,
                                 @RequestParam(required = false) String vklad){

        authorTerm.setId_term(termId);
        authorTerm.setId_author(authorId);
        authorTerm.setAuthor_vklad(vklad);

        authorTermRepository.save(authorTerm);

        Iterable<Term> terms = termRepository.findAll();
        Iterable<Author> authors = authorRepository.findAll();

        model.addAttribute("terms", terms);
        model.addAttribute("authors", authors);

        model.addAttribute("success", true);
        return "correct";
    }

}
