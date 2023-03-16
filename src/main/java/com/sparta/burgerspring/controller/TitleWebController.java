package com.sparta.burgerspring.controller;


import com.sparta.burgerspring.model.entities.Salary;
import com.sparta.burgerspring.model.entities.SalaryId;
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
    public String createTitle(@ModelAttribute("titleToCreate") Title newTitle, @ModelAttribute("titleIdToCreate") TitleId newTitleId) {
        newTitle.setId(newTitleId);
        if (newTitle.getToDate() == null) {
            newTitle.setToDate(LocalDate.of(9999, 01, 01));
        }
        titleRepository.saveAndFlush(newTitle);
        return "title/add-title-success";
    }

    @GetMapping("titles/delete")
    public String getTitleToDelete() {
        return "title/title-delete-form";
    }

    @PostMapping("/deleteTitle")
    public String findTitleToDelete(@ModelAttribute("titleToDelete") TitleId foundTitle) {
        return "redirect:/titles/delete/" + foundTitle.getEmpNo() + "/" + foundTitle.getTitle() + "/" + foundTitle.getFromDate();
    }

    @GetMapping("/titles/delete/{id}/{title}/{fromDate}")
    public String deleteTitle(@PathVariable Integer id, @PathVariable String title, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate) {
        TitleId titleId = new TitleId();
        titleId.setEmpNo(id);
        titleId.setTitle(title);
        titleId.setFromDate(fromDate);
        titleRepository.deleteById(titleId);
        return "title/title-delete-success";
    }

    @GetMapping("/titles/edit/{id}/{title}/{fromDate}")
    public String getUpdatedSalaryDetails(@PathVariable Integer id,@PathVariable String title,@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate, Model model) {
        TitleId titleId = new TitleId();
        titleId.setEmpNo(id);
        titleId.setTitle(title);
        titleId.setFromDate(fromDate);
        Title updatedTitle = titleRepository.findById(titleId).orElse(null);
        model.addAttribute("titleToEdit", updatedTitle);
        return "title/title-edit-form";
    }

    @PostMapping("/updatetitle")
    public String updateSalary(@ModelAttribute("updatedSalary")Title updatedTitle, @ModelAttribute("salaryIdToCreate")TitleId newTitleId){
        if(updatedTitle.getToDate() == null){
            updatedTitle.setToDate(LocalDate.of(9999, 01, 01));
        }
        titleRepository.saveAndFlush(updatedTitle);
        return "title/title-edit-success";
    }

}
