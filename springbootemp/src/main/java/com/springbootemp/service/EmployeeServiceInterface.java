package com.springbootemp.service;

import java.util.List;

import com.springbootemp.entity.Employee;
import com.springbootemp.exceptions.BusinessException;

public interface EmployeeServiceInterface {

	public Employee addEmployee(Employee employee) throws BusinessException;

	public List<Employee> getAllEmployees();
	Employee getEmployeeById(long id);


	public Employee updateEmployee(Employee employee, long id);

	void deleteEmployee(long id);


}
