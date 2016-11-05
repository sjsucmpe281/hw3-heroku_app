package com.cmpe281.hw3.controllers;

import com.cmpe281.hw3.models.*;
import com.cmpe281.hw3.services.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.*;

import javax.inject.*;

@RestController
@RequestMapping({ "cmpe281harishkumarkv013/rest" })
public class EmployeeController {

    @Inject
    EmployeeService employeeService;

/*    @PostConstruct
    public void init()  {
        System.out.println("Inside Init Method");
        employeeService = new EmployeeService ();
    }*/

    @RequestMapping(
            value = "/employee",
            method = RequestMethod.GET,
            headers = "Accept=*/*",
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    public ResponseEntity<?> getAllEmployees()   {
        try {
            EmployeeList employeeList = employeeService.fetchAll();
            return new ResponseEntity<> (employeeList, HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(
            value = "/employee/{id}",
            method = RequestMethod.GET,
            headers = "Accept=*/*",
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    public ResponseEntity<?> getEmployee(@PathVariable(value="id") Integer id)   {
        System.out.println("ID = " + id);
        try {
            Employee employee = employeeService.fetch(id);
            return new ResponseEntity<> (employee, HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(
            value = "/employee",
            method = RequestMethod.POST,
            headers = "Accept=*/*",
            consumes = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee)    {
        System.out.println(employee.getId ());
        System.out.println(employee.getFirstName ());
        System.out.println(employee.getLastName ());
        try {
            employee = employeeService.add (employee);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(employee.getId()).toUri());
            return new ResponseEntity<>(employee, httpHeaders, HttpStatus.CREATED);
        }catch(Exception e) {
            System.out.println(e.getMessage ());
            return new ResponseEntity<> (HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(
            value = "/employee/{id}",
            method = RequestMethod.PUT,
            headers = "Accept=*/*",
            consumes = {
                MediaType.APPLICATION_XML_VALUE,
                MediaType.APPLICATION_JSON_VALUE
            }
    )
    public ResponseEntity<?> updateEmployee(@PathVariable(value="id") Integer id,
                               @RequestBody Employee employee)    {
        System.out.println(employee.getId ());
        System.out.println(employee.getFirstName ());
        System.out.println(employee.getLastName ());
        try {
            Employee result = employeeService.update (id, employee);
            return new ResponseEntity<> (result, HttpStatus.OK);
        }catch (Exception e)    {
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(
            value = "/employee/{id}",
            method = RequestMethod.DELETE
    )
    public ResponseEntity<?> deleteEmployee(@PathVariable(value="id") Integer id)    {
        System.out.println("ID = " + id);
        try {
            employeeService.delete (id);
            return new ResponseEntity<> (HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }
    }
}
