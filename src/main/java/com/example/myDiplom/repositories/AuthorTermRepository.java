package com.example.myDiplom.repositories;

import com.example.myDiplom.entities.AuthorTerm;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorTermRepository extends CrudRepository<AuthorTerm, Integer> {
    @Query("select at from AuthorTerm at where at.id_author = ?1")
    List<AuthorTerm> findByAuthorId(Integer authorId);

    @Query("select at from AuthorTerm at where at.id_term = ?1")
    List<AuthorTerm> findByTermId(Integer termId);
}
