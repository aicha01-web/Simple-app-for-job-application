package com.groupeis.cv.controller;

import com.groupeis.cv.entity.ExperienceProEntity;
import com.groupeis.cv.repository.ExperienceProRepository;
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
public class ExperienceProController {

    @Autowired
    private ExperienceProRepository experienceProRepository;

    @GetMapping(value = "/experience/add")
    public String add(ModelMap map) {
        ExperienceProEntity experience = new ExperienceProEntity();
        map.addAttribute("experience", experience);
        return "experience/add";
    }

    @GetMapping("/editExperience/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        ExperienceProEntity experience = experienceProRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id experience invalide:" + id));
        model.addAttribute("experience", experience);
        return "experience/edit";
    }

    @GetMapping("/deleteExperience/{id}")
    public String deleteClasse(@PathVariable("id") int id, Model model) {
        ExperienceProEntity experience = experienceProRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id experience invalide:" + id));
        experienceProRepository.deleteById(experience.getId());
        return "redirect:/experience/getAll";
    }

    @PostMapping("/updateExperience/{id}")
    public String updateUser(@PathVariable("id") int id, @Valid ExperienceProEntity experience,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            experience.setId(id);
            return "experience/edit";
        }

        experienceProRepository.save(experience);
        return "redirect:/experience/getAll";
    }

    @PostMapping(value = "/experience/save")
    public String save(ExperienceProEntity experienceProEntity) {
        experienceProRepository.save(experienceProEntity);
        return "redirect:/cv/add";
    }

    @GetMapping(value = "/experience/getAll")
    public String getAll(ModelMap map) {
        map.addAttribute("experiences", experienceProRepository.findAll());

        return "experience/list";
    }
}
