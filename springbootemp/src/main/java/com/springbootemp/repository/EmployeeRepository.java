package com.springbootemp.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.springbootemp.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long>{
	
}

