package com.groupeis.cv.repository;


import com.groupeis.cv.entity.CvUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CvUserRepository extends JpaRepository<CvUserEntity,Integer> {
    CvUserEntity findByName(String name);
}
