package com.groupeis.cv.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Experience implements Serializable{
    private int id;
    private Date startAt;
    private Date endAt;
    private String enterprise;
    private String title;
    private Cv cv;
}
