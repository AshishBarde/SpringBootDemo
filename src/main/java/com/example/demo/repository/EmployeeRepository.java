package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	Employee findByEmployeeId(Integer id);

	void deleteByEmployeeId(Integer id);
}
