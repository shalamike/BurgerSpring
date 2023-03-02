package com.sparta.burgerspring.model.repositories;

import com.sparta.burgerspring.model.entities.Salary;
import com.sparta.burgerspring.model.entities.SalaryId;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SalaryRepository extends JpaRepository<Salary, SalaryId> {


    @Query(value = "select avg(a.salary)" +
            " from Salary a " +
            "left join Employee b on a.empNo.id = b.id " +
            "left join DeptEmp c on a.empNo.id = c.id.empNo " +
            "left join Department d on c.deptNo = d.id " +
            "where b.gender =:Gender and d.deptName = :dept")
    double findSalaryByGenderAndDept(String Gender, String dept);
    @Query(value = "select avg(a.salary)" +
            " from Salary a " +
            "left join Employee b on a.empNo = b " +
            "where b.gender =:Gender")
    double findSalaryByGender(String Gender);

}