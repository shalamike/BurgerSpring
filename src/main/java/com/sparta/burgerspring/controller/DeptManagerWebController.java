package com.sparta.burgerspring.controller;


import com.sparta.burgerspring.model.entities.DeptManager;
import com.sparta.burgerspring.model.entities.DeptManagerId;
import com.sparta.burgerspring.model.entities.Title;
import com.sparta.burgerspring.model.entities.TitleId;
import com.sparta.burgerspring.model.repositories.DeptManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
public class DeptManagerWebController {

    private DeptManagerRepository deptManagerRepository;

    @Autowired
    public DeptManagerWebController(DeptManagerRepository deptManagerRepository){
        this.deptManagerRepository = deptManagerRepository;
    }

    @GetMapping("/department-managers")
    public String getTitle(Model model) {
        model.addAttribute("managers", deptManagerRepository.findAll());
        return "manager/manager";
    }

    @GetMapping("/department-managers/create")
    public String getDeptMgrToCreate() {
        return "manager/manager-create-form";
    }

    @PostMapping("/createManager")
    public String createTitle(@ModelAttribute("deptMgrToCreate")DeptManager newDeptManager, @ModelAttribute("deptMgrIdToCreate")DeptManagerId newDeptManagerId) {
        newDeptManager.setId(newDeptManagerId);
        if (newDeptManager.getToDate() == null) {
            newDeptManager.setToDate(LocalDate.of(9999, 01, 01));
        }
        deptManagerRepository.saveAndFlush(newDeptManager);
        return "manager/add-manager-success";
    }
}
