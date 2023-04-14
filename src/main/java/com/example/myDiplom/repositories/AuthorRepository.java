package com.example.myDiplom.repositories;

import com.example.myDiplom.entities.Author;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface AuthorRepository extends CrudRepository<Author,Integer> {
    @Query("select a from Author a where a.fullname = ?1")
    Author findByFullname(String fullname);

    @Query("select a from Author a where a.fullname like %?1%")
    List<Author> findByFullnameContains(String fullname);
}
