package com.groupeis.cv.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CvUser implements Serializable {
    private int id;
    private String name;
    private String password;
    private Cv cv;
}
