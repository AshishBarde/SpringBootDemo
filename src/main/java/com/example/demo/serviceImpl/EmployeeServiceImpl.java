package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public Employee findByEmpId(Integer id) {
		// TODO Auto-generated method stub
		return employeeRepository.findByEmployeeId(id);
	}
	
	@Override
	public List<Employee> findAllEmp(){
		return  employeeRepository.findAll();
	}

	@Override
	public Boolean deleteByemployeeId(Integer id) {
		// TODO Auto-generated method stub
		employeeRepository.deleteByEmployeeId(id);
		return true;
	}

	@Override
	public int createEmployee(Employee employee) {
		// TODO Auto-generated method stub
		employeeRepository.save(employee);
		return 1;
	}

}
