package com.example.myDiplom.repositories;

import com.example.myDiplom.entities.Term;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TermRepository extends CrudRepository<Term, Integer> {
    @Query("select t from Term t where t.name = ?1")
    Term findByName(String name);

    @Query("select t from Term t where t.name like %?1%")
    List<Term> findByNameContains(String name);
}
