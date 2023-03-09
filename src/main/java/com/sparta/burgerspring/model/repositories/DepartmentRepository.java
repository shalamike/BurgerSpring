package com.sparta.burgerspring.model.repositories;

import com.sparta.burgerspring.model.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, String> {
    Department findByDeptName(String deptName);

    Department findDepartmentById(String id);

    @Query("SELECT AVG(s.salary) FROM DeptEmp de " +
            "JOIN Salary s ON s.empNo = de.id.empNo " +
            "JOIN Department d ON de.deptNo = d.id " +
            "WHERE d.deptName = :deptName AND (s.id.fromDate <= :date AND s.toDate >= :date)")
    Double getListOfSalariesByDept(String deptName, LocalDate date);

    @Query("SELECT e.firstName, e.lastName, d.deptName FROM Employee e " +
            "JOIN DeptEmp de ON de.id.empNo = e.id " +
            "JOIN Department d ON de.id.deptNo = d.id " +
            "WHERE e.firstName = :firstName " +
            "AND e.lastName = :lastName")
    List<String> getListOfDeptsByName(String firstName, String lastName);
    
}