package com.example.demo.model;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.type.BlobType;

import lombok.Data;

@Data
@Entity
@Table(name = "employeedemo", schema = "dbo")
public class Employee{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="EMPLOYEEID")
	Integer employeeId;
	
	@Column(name="FIRSTNAME")
	String firstName;
	
	@Column(name="LASTNAME")
	String lastName;
	
	@Column(name="SALARY")
	double salary;
	
	@Column(name="AGE")
	double age;
	
	@Column(name="EMPLOYEEINFO")
	byte [] EmployeeInfo;

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public double getAge() {
		return age;
	}

	public void setAge(double age) {
		this.age = age;
	}

	public byte[] getEmployeeInfo() {
		return EmployeeInfo;
	}

	public void setEmployeeInfo(byte[] employeeInfo) {
		EmployeeInfo = employeeInfo;
	}

}
