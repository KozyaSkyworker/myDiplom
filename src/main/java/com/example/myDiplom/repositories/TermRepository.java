package com.example.myDiplom.repositories;

import com.example.myDiplom.entities.Term;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TermRepository extends CrudRepository<Term, Integer> {
    @Query("select t from Term t where t.name = ?1")
    Term findByName(String name);
}
