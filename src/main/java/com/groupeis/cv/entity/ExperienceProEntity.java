package com.groupeis.cv.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "experiences")
public class ExperienceProEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Date startAt;

    @Column(nullable = false)
    private Date endAt;

    @Column(length = 50, nullable = false)
    private String enterprise;

    @Column(length = 200, nullable = false)
    private String title;
    @ManyToOne
    private CvEntity cv;
}
