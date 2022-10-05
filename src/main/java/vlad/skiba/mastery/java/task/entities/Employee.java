package vlad.skiba.mastery.java.task.entities;

import javax.persistence.*;
import java.util.Objects;

/**
 * Copyright Vlad Skiba (c) 2022.
 */
@Entity(name = "employees")
public class Employee {

    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    public Employee() {

    }

    public Employee(Long employeeId, String firstName, String lastName,
                    Long departmentId, String jobTitle, Gender gender, String dateOfBirth) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentId = departmentId;
        this.jobTitle = jobTitle;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(employeeId, employee.employeeId) &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(departmentId, employee.departmentId) &&
                Objects.equals(jobTitle, employee.jobTitle) &&
                gender == employee.gender &&
                Objects.equals(dateOfBirth, employee.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, firstName, lastName, departmentId, jobTitle, gender, dateOfBirth);
    }

    @Override
    public String toString() {
        return String.format("Employee: \nId: %d\nFirst Name: %s\nLast Name: %s\nDepartment Id: %d\n" +
                        "Job Title: %s\nGender: %s\nDate of birthday: %s\n\n",
                employeeId, firstName, lastName, departmentId, jobTitle, gender, dateOfBirth);
    }

}
