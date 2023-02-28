package com.groupeis.cv.controller;

import com.groupeis.cv.entity.CvEntity;
import com.groupeis.cv.entity.ExperienceProEntity;
import com.groupeis.cv.repository.CvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CvController {
    @Autowired
    private CvRepository cvRepository;

    @GetMapping(value = "/cv/add")
    public String add(ModelMap map) {
        CvEntity cv = new CvEntity();
        ExperienceProEntity exp = new ExperienceProEntity();
        map.addAttribute("cv", cv);
        map.addAttribute("experiences", exp);
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
    public String save(CvEntity cvEntity) {
        cvRepository.save(cvEntity);
        return "redirect:/cv/getAll";
    }

    @GetMapping(value = "/cv/getAll")
    public String getAll(ModelMap map) {
        map.addAttribute("cvs", cvRepository.findAll());

        return "cv/list";
    }
}
