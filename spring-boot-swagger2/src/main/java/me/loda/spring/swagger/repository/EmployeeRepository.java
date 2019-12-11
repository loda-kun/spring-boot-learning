package me.loda.spring.swagger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.loda.spring.swagger.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
