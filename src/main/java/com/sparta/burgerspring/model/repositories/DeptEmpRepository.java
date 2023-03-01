package com.sparta.burgerspring.model.repositories;

import com.sparta.burgerspring.model.entities.DeptEmp;
import com.sparta.burgerspring.model.entities.DeptEmpId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptEmpRepository extends JpaRepository<DeptEmp, DeptEmpId> {
}