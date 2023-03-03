package com.sparta.burgerspring.model.repositories;

import com.sparta.burgerspring.model.entities.DeptManager;
import com.sparta.burgerspring.model.entities.DeptManagerId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptManagerRepository extends JpaRepository<DeptManager, DeptManagerId> {


}