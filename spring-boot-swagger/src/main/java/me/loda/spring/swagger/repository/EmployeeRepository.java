package me.loda.spring.swagger.repository;

import me.loda.spring.swagger.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {
    private List<Employee> employees;
    public EmployeeRepository() {
        employees = new ArrayList<>();
        employees.add(new Employee(1,"Aom","Ptit","atomptit@gmail.com"));
        employees.add(new Employee(2,"Loda","Me","lodame@gmail.com"));
    }

    public Employee add(Employee employee) {
        employee.setId(employees.size()+1);
        employees.add(employee);
        return employee;
    }

    public void remove(Employee employee) {
        employees.remove(employee);
    }

    public List<Employee> findAll() {
        return employees;
    }
    public Employee findById(long id) {
        Employee employee = new Employee();
        employee.setId(id);
        if(employees.contains(employee)) {
            return employees.get(employees.indexOf(employee));
        }
        return null;
    }
}
