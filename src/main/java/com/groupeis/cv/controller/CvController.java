package com.groupeis.cv.controller;

import com.groupeis.cv.entity.CvEntity;
import com.groupeis.cv.entity.CvUserEntity;
import com.groupeis.cv.entity.ExperienceProEntity;
import com.groupeis.cv.repository.CvRepository;
import com.groupeis.cv.repository.CvUserRepository;
import com.groupeis.cv.repository.ExperienceProRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class CvController {
    @Autowired
    private CvRepository cvRepository;

    @Autowired
    private ExperienceProRepository experienceProRepository;

    @Autowired
    private CvUserRepository cvUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping(value = "/cv/add")
    public String add(ModelMap map) {
        CvEntity cv = new CvEntity();
        CvUserEntity cvUser = new CvUserEntity();
        ExperienceProEntity experiences = new ExperienceProEntity();
        map.addAttribute("cv", cv);
        map.addAttribute("user", cvUser);
        map.addAttribute("experiences",experiences);
        return "cv/add";
    }

    @GetMapping(value="/editCv/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CvEntity cv = cvRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id cv invalide:" + id));
        CvUserEntity user = cvUserRepository.findByName(auth.getName());
        model.addAttribute("cv", cv);
        model.addAttribute("experience",cv.getExperiences().get(0));
        model.addAttribute("userId",user.getId());
        return "cv/edit";
    }

    @GetMapping("/view/{id}")
    public String deleteCv(@PathVariable("id") int id, Model model) {
        CvEntity cv = cvRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id cv invalide:" + id));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CvUserEntity user = cvUserRepository.findByName(auth.getName());
        model.addAttribute("cv",cv);
        model.addAttribute("exp",cv.getExperiences());
        model.addAttribute("userId",user.getId());
        return "cv/viewOne";
    }

    @PostMapping("/updateCv/{id}")
    public String updateUser(@PathVariable("id") int id, @Valid CvEntity cv,@Valid ExperienceProEntity experience,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            cv.setId(id);
            return "cv/edit";
        }
        cvRepository.save(cv);
//        experience.setId(experience.getId());
//        experienceProRepository.save(experience);
        return "redirect:/";
    }

    @PostMapping(value = "/cv/save")
    public String save(CvEntity cvEntity, CvUserEntity cvUserEntity,ExperienceProEntity experience) {
        cvRepository.save(cvEntity);
        cvUserEntity.setPassword(passwordEncoder.encode(cvUserEntity.getPassword()));
        cvUserEntity.setName(cvEntity.getFirstname()+" "+cvEntity.getLastname());
        cvUserEntity.setCv(cvEntity);
        cvUserRepository.save(cvUserEntity);
        experience.setCv(cvEntity);
        experienceProRepository.save(experience);
        return "redirect:/login";
    }

    @GetMapping(value = "/cv/getAll")
    public String getAll(ModelMap map) {
        map.addAttribute("cvs", cvRepository.findAll());

        return "cv/list";
    }

    @GetMapping("/acceuil")
    public String accueil(ModelMap map) {
        return "index";
    }

    @GetMapping("/")
    public String index(ModelMap map) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CvUserEntity user = cvUserRepository.findByName(auth.getName());
        map.addAttribute("cvs", cvRepository.findAll());
        map.addAttribute("userId",user.getId());
        return "cv/view";
    }

}
