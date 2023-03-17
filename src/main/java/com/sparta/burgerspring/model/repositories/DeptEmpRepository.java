package com.sparta.burgerspring.model.repositories;

import com.sparta.burgerspring.model.entities.Department;
import com.sparta.burgerspring.model.entities.DeptEmp;
import com.sparta.burgerspring.model.entities.DeptEmpId;
import com.sparta.burgerspring.model.entities.Employee;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import java.time.LocalDate;
import java.util.List;

public interface DeptEmpRepository extends JpaRepository<DeptEmp, DeptEmpId> {


    @Query("SELECT COUNT(e) FROM DeptEmp e INNER JOIN Department d" +
            " ON e.deptNo = d.id WHERE d.deptName = :deptName" +
            " AND e.fromDate >= :fromDate AND e.toDate <= :toDate")
    Integer getSizeOfDepartment(String deptName, LocalDate fromDate, LocalDate toDate);

    List<DeptEmp> findByFromDateIsBeforeAndToDateAfterAndDeptNo(LocalDate beforeDate, LocalDate afterDate, Department dept);
    DeptEmp findDeptEmpByDeptNoAndId(Department department, DeptEmpId deptEmpId);


}