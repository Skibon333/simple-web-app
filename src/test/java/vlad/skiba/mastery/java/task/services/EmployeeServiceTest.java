package vlad.skiba.mastery.java.task.services;

import vlad.skiba.mastery.java.task.entities.Employee;
import vlad.skiba.mastery.java.task.entities.Gender;
import vlad.skiba.mastery.java.task.exceptions.EmployeeNotFoundException;
import vlad.skiba.mastery.java.task.repositories.EmployeeRepository;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.MockitoAnnotations;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Copyright Vlad Skiba (c) 2022.
 */
@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepositoryMock;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreate() {
        Employee employeeFromMock = getEmployees().get(0);
        Mockito.when(employeeRepositoryMock.save(any(Employee.class))).thenReturn(employeeFromMock);
        Employee employee = employeeService.create(employeeFromMock);
        assertThat(employee.getFirstName()).isSameAs(employeeFromMock.getFirstName());
        verify(employeeRepositoryMock, times(1)).save(employeeFromMock);
    }

    @Test
    public void testUpdateThrowsEmployeeNotFoundException() {
        Employee employeeFromMock = getEmployees().get(0);
        Mockito.when(employeeRepositoryMock.findById(null)).thenThrow(EmployeeNotFoundException.class);
        try {
            Employee employee = employeeService.update(employeeFromMock, null);
        } catch (EmployeeNotFoundException ex) {
            assertThatExceptionOfType(EmployeeNotFoundException.class);
        }
        verify(employeeRepositoryMock, times(1)).findById(null);
        verify(employeeRepositoryMock, times(0)).save(employeeFromMock);
    }

    @Test
    public void testUpdateNormalBehavior() {
        Employee employeeFromMock = getEmployees().get(0);
        Mockito.when(employeeRepositoryMock.findById(1L)).thenReturn(Optional.ofNullable(employeeFromMock));
        Mockito.when(employeeRepositoryMock.save(any(Employee.class))).thenReturn(employeeFromMock);
        Employee employee = employeeService.update(employeeFromMock, 1L);
        assertThat(employee.getFirstName()).isSameAs(employeeFromMock.getFirstName());
        verify(employeeRepositoryMock, times(1)).findById(1L);
        verify(employeeRepositoryMock, times(1)).save(employeeFromMock);
    }

    @Test
    public void testFindAll() {
        List<Employee> employeesFromMock = getEmployees();
        Mockito.when(employeeRepositoryMock.findAll()).thenReturn(employeesFromMock);
        List<Employee> employees = employeeService.findAll();
        assertEquals(employees, employeesFromMock);
        assertEquals(4, employees.size());
        verify(employeeRepositoryMock, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        Employee employeeFromMock = getEmployees().get(0);
        Mockito.when(employeeRepositoryMock.findById(1L)).thenReturn(Optional.of(employeeFromMock));
        Employee employee = employeeService.findById(1L);
        assertEquals(employee, employeeFromMock);
        verify(employeeRepositoryMock, times(1)).findById(1L);
    }

    private List<Employee> getEmployees() {
        return Arrays.asList(
                new Employee(1L, "Alex", "Block", 1L, "Java Developer", Gender.MALE, "20.03.2000"),
                new Employee(2L, "Kate", "Block", 2L, "Front-End Developer", Gender.FEMALE, "20.03.2000"),
                new Employee(3L, "Alex", "Block", 3L, "Back-End Developer", Gender.MALE, "20.03.2000"),
                new Employee(4L, "Alex", "Block", 4L, "Full-Stack Developer", Gender.FEMALE, "20.03.2000")
        );
    }

}
