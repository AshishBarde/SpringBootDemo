package com.example.demo.crud;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.BaseResponse;
import com.example.demo.common.BaseResponseHelper;
import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeInfo;
import com.example.demo.service.EmployeeService;

import java.util.logging.Level;
import java.util.logging.Logger;


@RestController
@CrossOrigin
public class SimpleCrudOperation {

	private static final Logger LOG = Logger.getLogger(SimpleCrudOperation.class.getName());
	@Autowired
	private EmployeeService employeeService;
	

	private BaseResponseHelper<Employee> employeeHelper = new BaseResponseHelper<Employee>();


	 @RequestMapping("/api/v1/welcome")
	   public String index() {
	      LOG.log(Level.INFO, "Index API is calling");
	      return "Welcome Sleuth!";
	   }
	
	
	@RequestMapping(value = "/api/v1/getEmployeById/{empId}", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse<Employee>> getEmployeeById(
			@PathVariable("empId") Integer empId)
	     {
		LOG.log(Level.INFO, "getEmployeById API is calling");
		return new ResponseEntity<BaseResponse<Employee>>(
				employeeHelper.setGetResponse(employeeService.findByEmpId(empId)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/v1/getAllEmployee", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse<List<Employee>>> getAllEmployee()
	     {
		LOG.log(Level.INFO, "getAllEmployee API is calling");
		return new ResponseEntity<BaseResponse <List<Employee>>>(
				employeeHelper.setGetAllResponse(employeeService.findAllEmp()), HttpStatus.OK);
	}
	
	@RequestMapping(value ="/api/v1/deleteEmployeeById/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<BaseResponse<Boolean>> deleteByEmployeeId(@PathVariable("id") Integer id)
	{
		return new ResponseEntity<BaseResponse<Boolean>>(employeeHelper.setDeleteResponse(employeeService.deleteByemployeeId(id)),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/add/employee", method = RequestMethod.POST)
	public ResponseEntity<?> saveEmployeeInformation(@RequestBody Employee employee) throws IOException {

		  ByteArrayOutputStream bos = new ByteArrayOutputStream();
	      ObjectOutputStream oos = new ObjectOutputStream(bos);
	      EmployeeInfo info = new EmployeeInfo();
	      oos.writeObject(info);
	      oos.flush();
	      byte [] data = bos.toByteArray();
	      System.out.println(data);
	      employee.setEmployeeInfo(data);
	      
		int status = employeeService.createEmployee(employee);

		if (status > 0) {

			return new ResponseEntity<>(employee, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(employee, HttpStatus.BAD_REQUEST);
		}
	}
	
}
