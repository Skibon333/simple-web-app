package vlad.skiba.mastery.java.task.services;

import vlad.skiba.mastery.java.task.entities.Employee;
import vlad.skiba.mastery.java.task.entities.TransferableEntity;
import vlad.skiba.mastery.java.task.exceptions.EmployeeNotFoundException;
import vlad.skiba.mastery.java.task.jms.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import vlad.skiba.mastery.java.task.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.List;

/**
 * Copyright Vlad Skiba (c) 2022.
 */
@Service
public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private MessageSender messageSender;

    public Employee create(Employee newEmployee) {
        Employee createdEmployee = employeeRepository.save(newEmployee);
        LOGGER.debug("A new employee with id " + newEmployee.getEmployeeId() + " is created in the database");
        return createdEmployee;
    }

    public Employee update(Employee newEmployee, Long employeeId) {
        return employeeRepository.findById(employeeId)
                .map(employee -> {
                    employee.setFirstName(newEmployee.getFirstName());
                    employee.setLastName(newEmployee.getLastName());
                    employee.setDepartmentId(newEmployee.getDepartmentId());
                    employee.setJobTitle(newEmployee.getJobTitle());
                    employee.setGender(newEmployee.getGender());
                    employee.setDateOfBirth(newEmployee.getDateOfBirth());
                    Employee updatedEmployee = employeeRepository.save(employee);
                    LOGGER.debug("Employee with id " + employeeId + " was updated");
                    return updatedEmployee;
                })
                .orElseThrow(() -> {
                    LOGGER.warn("Employee with id " + employeeId + " doesn't exist!");
                    return new EmployeeNotFoundException("Employee with id " + employeeId + " doesn't exist!");
                });
    }

    public List<Employee> findAll() {
        List<Employee> employees = (List<Employee>) employeeRepository.findAll();
        LOGGER.debug("Employees were returned from the database");
        return employees;
    }

    public Employee findById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> {
                    LOGGER.warn("Employee with id " + employeeId + " doesn't exist!");
                    return new EmployeeNotFoundException("Employee with id " + employeeId + " doesn't exist!");
                });
        LOGGER.debug("Employee with id " + employeeId + " was returned from the database");
        return employee;
    }

    public void deleteById(Long employeeId) {
        employeeRepository.findById(employeeId).ifPresentOrElse(
                ignored -> {
                    employeeRepository.deleteById(employeeId);
                    LOGGER.debug("Employee with id " + employeeId + " has been deleted");
                },
                () -> {
                    LOGGER.warn("Employee with id " + employeeId + " doesn't exist!");
                    throw new EmployeeNotFoundException("Employee with id " + employeeId + " doesn't exist!");
                }
        );
    }

    public void deleteAll() {
        employeeRepository.deleteAll();
        LOGGER.debug("Employees have been removed from the database");
    }

    public void updateJobTitleByDepartmentId(Long departmentId, String jobTitle) {
        employeeRepository.findByDepartmentId(departmentId).ifPresentOrElse(
                ignored -> {
                    employeeRepository.updateJobTitleByDepartmentId(departmentId, jobTitle);
                    LOGGER.debug("The job title of employees from the department with id " + departmentId + " has been changed to " + jobTitle);
                },
                () -> LOGGER.warn("Employees with department id " + departmentId + " don't exist!")
        );
    }

    public void handleSendMessage(TransferableEntity entity) {
        messageSender.sendMessage(entity);
    }

}
