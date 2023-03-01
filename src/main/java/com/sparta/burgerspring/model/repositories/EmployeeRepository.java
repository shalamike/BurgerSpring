package com.sparta.burgerspring.model.repositories;

import com.sparta.burgerspring.model.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}