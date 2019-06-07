package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Employee;

public interface EmployeeService {
	Employee findByEmpId(Integer id);

	List<Employee> findAllEmp();

	Boolean deleteByemployeeId(Integer id);

	int createEmployee(Employee employee);
}
