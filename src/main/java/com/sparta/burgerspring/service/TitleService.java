package com.sparta.burgerspring.service;

import com.sparta.burgerspring.model.entities.Salary;
import com.sparta.burgerspring.model.repositories.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TitleService {

    private final TitleRepository titleRepository;

    @Autowired
    public TitleService(TitleRepository titleRepository) {
        this.titleRepository = titleRepository;
    }

    public List<Salary> getSalariesInYearByJobTitle(String title, String year){
        return titleRepository.getSalariesInYearByJobTitle(title, year);
    }
}
