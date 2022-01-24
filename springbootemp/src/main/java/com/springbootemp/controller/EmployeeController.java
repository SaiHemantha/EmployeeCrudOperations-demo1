package com.springbootemp.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootemp.entity.Employee;
import com.springbootemp.exceptions.BusinessException;
import com.springbootemp.exceptions.ControllerException;
import com.springbootemp.service.EmployeeServiceInterface;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeServiceInterface employeeServiceInterface;
	
	@PostMapping
	public ResponseEntity<?> addEmployee(@RequestBody Employee employee){
		try {
			Employee employeeSaved = employeeServiceInterface.addEmployee(employee);
			return new ResponseEntity<Employee>(employeeSaved, HttpStatus.CREATED);
		}catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			ControllerException ce = new ControllerException("704","Something went wrong in controller");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(){
		
		List<Employee> listOfAllEmps = employeeServiceInterface.getAllEmployees();
		return new ResponseEntity<List<Employee>>(listOfAllEmps, HttpStatus.OK);
	}
	
	//build get employee by id REST API
		@GetMapping("{id}")
		public ResponseEntity<Employee> getEmployeeById(@PathVariable("id")  long employeeId){
			return new ResponseEntity<Employee>(employeeServiceInterface.getEmployeeById(employeeId),HttpStatus.OK);
		}
	
	@PutMapping("{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id,@RequestBody Employee employee){
	    return new ResponseEntity<Employee>(employeeServiceInterface.updateEmployee(employee, id), HttpStatus.OK);
	}
	
	//build delete employee REST API
		@DeleteMapping("{id}")
		public ResponseEntity<String> deleteEmployee(@PathVariable("id")  long id){
			//delete employee from DB
			employeeServiceInterface.deleteEmployee(id);
		return new ResponseEntity<String>("Employee deleted successfully!.",HttpStatus.OK);
			
		}
	
	
	
	
}