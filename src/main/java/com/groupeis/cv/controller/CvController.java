package com.groupeis.cv.controller;

import com.groupeis.cv.domain.ExperienceList;
import com.groupeis.cv.entity.CvEntity;
import com.groupeis.cv.entity.CvUserEntity;
import com.groupeis.cv.entity.ExperienceProEntity;
import com.groupeis.cv.repository.CvRepository;
import com.groupeis.cv.repository.CvUserRepository;
import com.groupeis.cv.repository.ExperienceProRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CvController {
    @Autowired
    private CvRepository cvRepository;

    @Autowired
    private ExperienceProRepository experienceProRepository;

    @Autowired
    private CvUserRepository cvUserRepository;

    @GetMapping(value = "/cv/add")
    public String add(ModelMap map) {
        CvEntity cv = new CvEntity();
        CvUserEntity cvUser = new CvUserEntity();
        ExperienceProEntity experiences = new ExperienceProEntity();
        map.addAttribute("cv", cv);
        map.addAttribute("user", cvUser);
        map.addAttribute("experiences",cv.getExperiences());
        return "cv/add";
    }

    @GetMapping("/editCv/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        CvEntity cv = cvRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id cv invalide:" + id));
        model.addAttribute("cv", cv);
        return "cv/edit";
    }

//    @GetMapping("/deleteCv/{id}")
//    public String deleteCv(@PathVariable("id") int id, Model model) {
//        CvEntity cv = cvRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Id cv invalide:" + id));
//        cvRepository.deleteById(cv.getId());
//        return "redirect:/cv/getAll";
//    }

    @PostMapping("/updateCv/{id}")
    public String updateUser(@PathVariable("id") int id, @Valid CvEntity cv,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            cv.setId(id);
            return "cv/edit";
        }

        cvRepository.save(cv);
        return "redirect:/cv/getAll";
    }

    @PostMapping(value = "/cv/save")
    public String save(CvEntity cvEntity, CvUserEntity cvUserEntity,ExperienceProEntity experience) {
        cvRepository.save(cvEntity);
        cvUserEntity.setName(cvEntity.getFirstname()+" "+cvEntity.getLastname());
        cvUserEntity.setCv(cvEntity);
        cvUserRepository.save(cvUserEntity);
        experienceProRepository.save(experience);
        return "redirect:/";
    }

    @GetMapping(value = "/cv/getAll")
    public String getAll(ModelMap map) {
        map.addAttribute("cvs", cvRepository.findAll());

        return "cv/list";
    }

    @GetMapping("/")
    public String index(ModelMap map) {
        return "index";
    }

}
