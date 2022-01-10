package com.react.spring.crud.controllers;

import com.react.spring.crud.exceptions.ResourceNotFoundException;
import com.react.spring.crud.models.Employee;
import com.react.spring.crud.repositories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

	@Autowired
	private EmployeeRepo employeeRepo;

	@GetMapping(path = "/getAllEmployees")
	public List<Employee> getAllEmployees() {
		return employeeRepo.findAll();
	}

	@PostMapping(path = "/createEmploye")
	public boolean createEmploye(@RequestBody Employee employee) {
		System.out.println(employee.getFirstName());
		Employee emp = employeeRepo.save(employee);
		if (emp instanceof Employee) {
			return true;
		} else {
			return false;
		}
	}

	@GetMapping(path = "/getEmployeeById/{id}")
	public ResponseEntity<Employee> getEmployeById(@PathVariable Long id) {
		Employee emp = employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("id not found" + id));
		return ResponseEntity.ok(emp);

	}

	@PutMapping(path = "updateEmployee")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
		Employee emp = employeeRepo.findById(employee.getId())
				.orElseThrow(() -> new ResourceNotFoundException("employe not found" + employee.getId()));
		emp.setFirstName(employee.getFirstName());
		emp.setLastName(employee.getLastName());
		emp.setEmail(employee.getEmail());
		employeeRepo.save(emp);
		return ResponseEntity.ok(emp);
	}
	
	@DeleteMapping(path = "/deleteEmployeeById/{id}")
	public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
		Employee emp = employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("id not found" + id));
		employeeRepo.delete(emp);
		return ResponseEntity.ok(true);
	}
	
}
