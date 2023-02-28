package com.groupeis.cv.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cv")
public class CvEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 150, nullable = false)
    private String lastname;

    @Column(length = 150, nullable = false)
    private String firstname;

    @Column(length = 150, nullable = false)
    private String email;

    @Column(length = 30, nullable = false)
    private int telephone;

    @Column(length = 150, nullable = false)
    private String specialite;

    @Column(length = 150, nullable = false)
    private String niveauEetude;

    @OneToMany(mappedBy = "cv")
    private List<ExperienceProEntity> experiences;


}
