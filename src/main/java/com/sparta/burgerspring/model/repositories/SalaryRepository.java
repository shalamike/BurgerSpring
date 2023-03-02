package com.sparta.burgerspring.model.repositories;

import com.sparta.burgerspring.model.entities.Employee;
import com.sparta.burgerspring.model.entities.Salary;
import com.sparta.burgerspring.model.entities.SalaryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    @Query(value = "Select e from Employee e, Salary s "+
                " where e.id = s.id.empNo and s.salary >= :salary ")
    List<Employee> findSalariesAboveCertainSalary(@Param("salary")Integer salary);


    @Query(value = "Select max(s.salary) from Employee e, Salary s where s.id.empNo = e.id and e.id = :empId ")
    Integer highestSalaryOfGivenEmployeeId(@Param("empId") Integer empId);


}