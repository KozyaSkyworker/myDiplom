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
import java.util.Objects;
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

    @GetMapping("/")
    public String getMainPage(Model model){
        Iterable<Term> terms = termRepository.findByDate();

        model.addAttribute("terms", terms);

        return "index";
    }

    // GET SEARCH

    @GetMapping("/search")
    public String getSearch(@RequestParam(required = false) String isTerm,
                            @RequestParam(required = false) String isAuthor,
                            @RequestParam(required = false) String name, Model model) {

        System.out.println(isTerm);
        System.out.println(isAuthor);

        Term term = termRepository.findByName(name);

        if (term == null){
            if (isTerm != null){
                List<Term> termList = termRepository.findByNameContains(name);
                if(termList.size() != 0){
                    model.addAttribute("termList", termList);
                }
            }
            if(isAuthor != null){
                List<Author> authorList = authorRepository.findByFullnameContains(name);
                if(authorList.size() != 0){
                    model.addAttribute("authorList", authorList);
                }
            }

            model.addAttribute("result", name);
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
        model.addAttribute("moderator", false);
        return "detailed/termPage";
    }

    // GET TERM

    @GetMapping("/term/{id}")
    public String getTermById(@PathVariable("id") Integer id, Model model){
        Optional<Term> term = termRepository.findById(id);

        Iterable<AuthorTerm> authorTerm = authorTermRepository.findAll();



        for(AuthorTerm at : authorTerm){
            if(at.getId_term().equals(term.get().getId_term())){
                Integer authorId = at.getId_author();

                Optional<Author> author = authorRepository.findById(authorId);

                model.addAttribute("author", author.get());
                model.addAttribute("authorTerm", at);
            }
        }


        model.addAttribute("term", term.get());
        model.addAttribute("moderator", false);

        return  "detailed/termPage";
    }

    // GET AUTHOR

    @GetMapping("/author/{id}")
    public String getAuthorById(@PathVariable("id") Integer id, Model model){
        Optional<Author> author = authorRepository.findById(id);
        model.addAttribute("author", author.get());
        model.addAttribute("moderator", false);
        return  "detailed/authorPage";
    }

    // AUTHORS COMPARE PAGE

    @GetMapping("/author/compare")
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

    // GET AUTHORIZATION

    @GetMapping("/authorization")
    public String getAuthorizationPage(){
        return "authorization";
    }
}