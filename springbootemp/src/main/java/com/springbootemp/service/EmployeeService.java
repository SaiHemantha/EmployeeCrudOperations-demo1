package com.springbootemp.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootemp.entity.Employee;
import com.springbootemp.exceptions.BusinessException;
import com.springbootemp.exceptions.ResourceNotFoundException;
import com.springbootemp.repository.EmployeeRepository;

@Service
public class EmployeeService implements EmployeeServiceInterface{
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee addEmployee(Employee employee) {

		if(employee.getFirstName().isEmpty() || employee.getFirstName().length() == 0 && employee.getLastName().isEmpty()||employee.getLastName().length()==0&&employee.getEmail().isEmpty()|| employee.getEmail().length()==0) {
			throw new BusinessException("701","Please send proper name, It blank");
		}
		try {
			Employee savedEmployee = employeeRepository.save(employee);
			return savedEmployee;
		}
		   catch (IllegalArgumentException e) {
				throw new BusinessException("702","given employee is null" + e.getMessage());
		    }catch (Exception e) {
			throw new BusinessException("703","Something went wrong in Service layer while saving the employee" + e.getMessage());
		}


	}
	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}
	@Override
	public Employee getEmployeeById(long id) {
		//Optional<Employee> employee=employeeRepository.findById(id);
		//if(employee.isPresent()) {
		//	return employee.get();
		//}else {
		//	throw new ResourceNotFoundException("Employee","Id",id);
		//}
		return employeeRepository.findById(id).orElseThrow(() -> 
		                    new ResourceNotFoundException("Employee","Id",id));
	}
	@Override
	public Employee updateEmployee(Employee employee, long id) {
		
		//we need to check whether employee with given id is exist in DB or not
		Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Employee","Id",id));
		
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		//save existing employee to DB
		employeeRepository.save(existingEmployee);
		return existingEmployee;
	}
	@Override
	public void deleteEmployee(long id) {
		//check whether a employee exist in a DB or not
		employeeRepository.findById(id).orElseThrow(() ->
		                                 new ResourceNotFoundException("Employee","Id",id));
		employeeRepository.deleteById(id);
}
}
	
	
	
	
	



