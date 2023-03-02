package com.sparta.burgerspring.model.repositories;

import com.sparta.burgerspring.model.entities.Salary;
import com.sparta.burgerspring.model.entities.Title;
import com.sparta.burgerspring.model.entities.TitleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TitleRepository extends JpaRepository<Title, TitleId> {

    @Query(value = "select s from Salary s, Title t where t.empNo = s.empNo and s.toDate >= \'1999-01-01\' and  s.toDate <= \'1999-12-31\' and s.id.fromDate >= \'1999-01-01\' and s.id.fromDate <=\'1999-12-31\' and t.id.title = :title")
    List<Salary> getSalariesInYear(@Param("title") String title);

}