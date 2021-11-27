package com.example.happy.hr.repositories;

import com.example.happy.hr.domain.entities.ProjectCard;
import com.example.happy.hr.repositories.custom.CustomProjectCardRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectCardRepository extends JpaRepository<ProjectCard, Integer>, CustomProjectCardRepository {
}
