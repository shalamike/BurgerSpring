package com.sparta.burgerspring.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DeptManagerId implements Serializable {
    private static final long serialVersionUID = 8767449750959919275L;
    @Column(name = "emp_no", nullable = false)
    private Integer empNo;

    @Column(name = "dept_no", nullable = false, length = 4)
    private String deptNo;

    public Integer getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Integer empNo) {
        this.empNo = empNo;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DeptManagerId entity = (DeptManagerId) o;
        return Objects.equals(this.empNo, entity.empNo) &&
                Objects.equals(this.deptNo, entity.deptNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empNo, deptNo);
    }

    @Override
    public String toString() {
        return "DeptManagerId{" +
                "empNo=" + empNo +
                ", deptNo='" + deptNo + '\'' +
                '}';
    }
}