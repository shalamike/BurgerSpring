package com.sparta.burgerspring.model.repositories;

import com.sparta.burgerspring.model.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT e FROM Employee e WHERE e.lastName = ?1")
    List<Employee> findByLastName(String lastName);

    Employee getEmployeeById(Integer id);

    List<Employee> findByFirstName(String firstName);

    List<Employee> findByFirstNameAndAndLastName(String firstName, String lastName);


}