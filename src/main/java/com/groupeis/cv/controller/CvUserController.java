package com.groupeis.cv.controller;

import com.groupeis.cv.entity.CvUserEntity;
import com.groupeis.cv.repository.CvUserRepository;
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
public class CvUserController {
    
    @Autowired
    private CvUserRepository cvUserRepository;

    @GetMapping(value = "/cvUser/add")
    public String add(ModelMap map) {
        CvUserEntity cv = new CvUserEntity();
        map.addAttribute("cv", cv);
        return "cvUser/add";
    }

    @GetMapping("/editCvUser/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        CvUserEntity cvUser = cvUserRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id cv invalide:" + id));
        model.addAttribute("cv", cvUser);
        return "cvUSer/edit";
    }

//    @GetMapping("/deleteCv/{id}")
//    public String deleteCv(@PathVariable("id") int id, Model model) {
//        CvUserEntity cv = cvUserRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Id cv invalide:" + id));
//        cvUserRepository.deleteById(cv.getId());
//        return "redirect:/cv/getAll";
//    }

    @PostMapping("/updateCvUser/{id}")
    public String updateUser(@PathVariable("id") int id, @Valid CvUserEntity cv,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            cv.setId(id);
            return "cvUser/edit";
        }

        cvUserRepository.save(cv);
        return "redirect:/cvUser/getAll";
    }

    @PostMapping(value = "/cvUser/save")
    public String save(CvUserEntity CvUserEntity) {
        cvUserRepository.save(CvUserEntity);
        return "redirect:/cvUser/getAll";
    }

    @GetMapping(value = "/cvUser/getAll")
    public String getAll(ModelMap map) {
        map.addAttribute("cvs", cvUserRepository.findAll());

        return "cvUser/list";
    }
}
