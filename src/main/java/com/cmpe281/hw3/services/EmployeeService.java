package com.cmpe281.hw3.services;

import com.cmpe281.hw3.models.*;
import com.cmpe281.hw3.repository.*;
import org.springframework.stereotype.*;

import javax.inject.*;
import java.util.function.*;

/**
 * Harish Kumar K V
 */
@Service
public class EmployeeService {

    @Inject
    EmployeeRepository employeeRepository;

    public EmployeeService()    {}

    public Employee add(Employee employee) throws Exception {
        if (employeeRepository.findOne (employee.getId ()) != null) {
            throw new Exception ();
        }
        return employeeRepository.save (employee);
    }

    public Employee update (Integer id, Employee employee) throws Exception {
        employeeExists (id);
        return employeeRepository.save (employee);
    }

    private Employee employeeExists (Integer id) throws Exception {
        Employee employee = employeeRepository.findOne (id);
        if (employee == null) {
            throw new Exception ();
        }
        return employee;
    }

    public void delete (Integer id) throws Exception {
        employeeExists (id);
        employeeRepository.delete (id);
    }

    public Employee fetch (Integer id) throws Exception {
        return employeeExists (id);
    }

    public EmployeeList fetchAll () {
        Iterable<Employee> employees = employeeRepository.findAll ();
        final EmployeeList employeeList = new EmployeeList ();
        employees.forEach (new Consumer<Employee> () {
            @Override
            public void accept (Employee employee) {
                employeeList.getEmployees ().add (employee);
            }
        });
        return employeeList;
    }
}
