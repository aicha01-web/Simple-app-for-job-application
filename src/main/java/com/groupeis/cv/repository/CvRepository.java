package com.groupeis.cv.repository;

import com.groupeis.cv.entity.CvEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CvRepository extends JpaRepository<CvEntity,Integer> {
}
