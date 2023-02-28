package com.groupeis.cv.repository;

import com.groupeis.cv.entity.ExperienceProEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceProRepository extends JpaRepository<ExperienceProEntity,Integer> {
}
