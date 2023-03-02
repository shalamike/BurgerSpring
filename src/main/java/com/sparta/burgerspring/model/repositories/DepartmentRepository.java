package com.sparta.burgerspring.model.repositories;

import com.sparta.burgerspring.model.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, String> {
    Department findByDeptName(String deptName);

    @Query("SELECT AVG(s.salary) FROM DeptEmp de " +
            "JOIN Salary s ON s.empNo = de.id.empNo " +
            "JOIN Department d ON de.deptNo = d.id " +
            "WHERE d.deptName = :deptName AND (s.id.fromDate <= :date AND s.toDate >= :date)")
    Double getListOfSalariesByDept(String deptName, LocalDate date);
}