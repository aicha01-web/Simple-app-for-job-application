package com.groupeis.cv.domain;

import com.groupeis.cv.entity.ExperienceProEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cv implements Serializable{
    private int id;
    private String lastname;
    private String firstname;
    private String email;
    private int telephone;
    private String specialite;
    private String niveauEetude;
    private List<ExperienceProEntity> experiences;
}
