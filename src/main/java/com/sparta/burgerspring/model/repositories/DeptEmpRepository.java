package com.sparta.burgerspring.model.repositories;

import com.sparta.burgerspring.model.entities.Department;
import com.sparta.burgerspring.model.entities.DeptEmp;
import com.sparta.burgerspring.model.entities.DeptEmpId;
import com.sparta.burgerspring.model.entities.Employee;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DeptEmpRepository extends JpaRepository<DeptEmp, DeptEmpId> {
    List<DeptEmp> findByFromDateIsBeforeAndToDateAfterAndDeptNo(LocalDate beforeDate, LocalDate afterDate, Department dept);

}