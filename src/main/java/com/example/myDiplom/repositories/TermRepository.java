package com.example.myDiplom.repositories;

import com.example.myDiplom.entities.Term;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface TermRepository extends CrudRepository<Term, Integer> {
    @Query("select t from Term t where t.name = ?1")
    Term findByName(String name);

    @Query("select t from Term t where t.name like %?1%")
    List<Term> findByNameContains(String name);

    @Query("select t from Term t order by t.updateTimestamp desc limit 6")
    List<Term> findByDate();
}
