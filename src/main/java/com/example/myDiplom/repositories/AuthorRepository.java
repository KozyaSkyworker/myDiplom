package com.example.myDiplom.repositories;

import com.example.myDiplom.entities.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author,Integer> {
}
