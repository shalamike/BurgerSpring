package com.sparta.burgerspring.model.repositories;

import com.sparta.burgerspring.model.entities.Salary;
import com.sparta.burgerspring.model.entities.SalaryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SalaryRepository extends JpaRepository<Salary, SalaryId> {

    @Query(value = "select a from Salary a left join Employee b on a.empNo = b where b.gender =:Gender")
    List<Integer> findSalaryByGender(String Gender);

}