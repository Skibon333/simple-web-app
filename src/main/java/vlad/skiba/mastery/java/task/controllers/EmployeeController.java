package vlad.skiba.mastery.java.task.controllers;

import vlad.skiba.mastery.java.task.entities.Employee;
import vlad.skiba.mastery.java.task.entities.TransferableEntity;
import org.springframework.beans.factory.annotation.Autowired;
import vlad.skiba.mastery.java.task.services.EmployeeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

import javax.validation.Valid;
import java.util.List;

/**
 * Copyright Vlad Skiba (c) 2022.
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> findAll() {
        final List<Employee> employees = employeeService.findAll();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{employeeId}")
    @ApiResponses(value = @ApiResponse(code = 404, message = "Employee not found"))
    public ResponseEntity<Employee> findById(@PathVariable Long employeeId) {
        final Employee employee = employeeService.findById(employeeId);
        return (employee != null)
                ? new ResponseEntity<>(employee, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @ApiResponses(value = @ApiResponse(code = 201, message = "Employee was successfully created"))
    public ResponseEntity<Employee> create(@RequestBody @Valid Employee newEmployee) {
        employeeService.create(newEmployee);
        return (newEmployee != null)
                ? new ResponseEntity<>(newEmployee, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{employeeId}")
    @ApiResponses(@ApiResponse(code = 404, message = "Employee not found"))
    public ResponseEntity<Employee> update(@RequestBody @Valid Employee newEmployee, @PathVariable Long employeeId) {
        employeeService.update(newEmployee, employeeId);
        return (newEmployee != null)
                ? new ResponseEntity<>(newEmployee, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping
    @ApiResponses(@ApiResponse(code = 202, message = "Accepted"))
    public ResponseEntity<?> updateJobTitleByDepartmentId(@RequestBody @Valid TransferableEntity entity) {
        employeeService.handleSendMessage(entity);
        return new ResponseEntity<>(entity, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{employeeId}")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Employee was successfully deleted"),
            @ApiResponse(code = 404, message = "Employee not found")
    })
    public ResponseEntity<?> deleteById(@PathVariable Long employeeId) {
        employeeService.deleteById(employeeId);
        return (employeeId != null)
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping
    @ApiResponses(value = @ApiResponse(code = 204, message = "Employees was successfully deleted"))
    public ResponseEntity<?> deleteAll() {
        employeeService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
