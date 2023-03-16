package com.sparta.burgerspring.controller;


import com.sparta.burgerspring.model.entities.Title;
import com.sparta.burgerspring.model.entities.TitleId;
import com.sparta.burgerspring.model.repositories.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class TitleWebController {

    private TitleRepository titleRepository;

    @Autowired
    public TitleWebController(TitleRepository titleRepository) {
        this.titleRepository = titleRepository;
    }

    @GetMapping("/titles")
    public String getTitle() {
        return "title/search-form";
    }


    @GetMapping("/titles/search/")
    public String getTitleDetails(Model model, @RequestParam Integer empNo) {
        model.addAttribute("titles", titleRepository.getDetailsByEmpNo(empNo));
        return "title/search-results";
    }

    @GetMapping("/titles/create")
    public String getTitleToCreate() {
        return "title/title-create-form";
    }

    @PostMapping("/createTitle")
    public String createTitle(@ModelAttribute("titleToCreate")Title newTitle, @ModelAttribute("titleIdToCreate")TitleId newTitleId){
        newTitle.setId(newTitleId);
        if(newTitle.getToDate() == null){
            newTitle.setToDate(LocalDate.of(9999, 01, 01));
        }
        titleRepository.saveAndFlush(newTitle);
        return "title/add-title-success";
    }

    @GetMapping(value = "/titles/delete/{id}/{title}")
    public String delete(@PathVariable("id") Integer id, @PathVariable("title") String title){
        TitleId titleId = new TitleId();
        titleRepository.deleteById();
        return "title/delete-success";
    }












}
