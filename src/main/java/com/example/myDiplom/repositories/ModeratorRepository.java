package com.example.myDiplom.repositories;

import com.example.myDiplom.entities.Moderator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeratorRepository extends JpaRepository<Moderator, Long> {
    Moderator findByLogin(String login);
}
