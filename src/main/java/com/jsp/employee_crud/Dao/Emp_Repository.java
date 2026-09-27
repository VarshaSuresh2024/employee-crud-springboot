package com.jsp.employee_crud.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.employee_crud.Entity.Employee;

public interface Emp_Repository extends JpaRepository<Employee, Integer>{

}
