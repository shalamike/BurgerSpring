package com.sparta.burgerspring.model.entities;

import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "C")
    @Column(name = "dept_no", nullable = false, length = 4)
    private String id;

    @Column(name = "dept_name", nullable = false, length = 40)
    private String deptName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id='" + id + '\'' +
                ", deptName='" + deptName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return id.equals(that.id) && deptName.equals(that.deptName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deptName);
    }
}