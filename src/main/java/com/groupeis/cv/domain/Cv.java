package com.groupeis.cv.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cv implements Serializable{

    private int id;
    private String lastname;
    private String firstname;
    private String email;
    private int telephone;
    private String specialite;
    private String niveauEetude;
}
