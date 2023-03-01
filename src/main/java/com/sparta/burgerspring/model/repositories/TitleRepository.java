package com.sparta.burgerspring.model.repositories;

import com.sparta.burgerspring.model.entities.Salary;
import com.sparta.burgerspring.model.entities.Title;
import com.sparta.burgerspring.model.entities.TitleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TitleRepository extends JpaRepository<Title, TitleId> {

    @Query(value = "select s from Salary s, Title t \n" +
            "where t.empNo =  s.empNo \n" +
            "and s.toDate = t.toDate \n"+
            "and s.id.fromDate = t.id.fromDate \n" +
            "and year( s.toDate) = :year and year( s.toDate )= :year \n" +
            "and year(s.id.fromDate) = :year and year(s.id.fromDate) = :year \n" +
            "and t.id.title = :title")
    List<Salary> getSalariesInYear(@Param("title") String title,@Param("year") String year);

}