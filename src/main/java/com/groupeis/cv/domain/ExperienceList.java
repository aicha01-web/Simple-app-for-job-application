package com.groupeis.cv.domain;

import com.groupeis.cv.entity.ExperienceProEntity;

import java.util.ArrayList;
import java.util.List;

public class ExperienceList {
    private ArrayList<ExperienceProEntity> expList;

    public List<ExperienceProEntity> getClientList(){
        return expList;
    }

    public void setClientList(ArrayList<ExperienceProEntity> clients){
        this.expList = clients;
    }
}
