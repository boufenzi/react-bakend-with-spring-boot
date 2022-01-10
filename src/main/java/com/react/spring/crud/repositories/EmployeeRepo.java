package com.react.spring.crud.repositories;

import com.react.spring.crud.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "Employees")
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
}
