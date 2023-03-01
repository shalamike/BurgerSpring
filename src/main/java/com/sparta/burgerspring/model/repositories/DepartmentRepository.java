package com.sparta.burgerspring.model.repositories;

import com.sparta.burgerspring.model.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, String> {
}