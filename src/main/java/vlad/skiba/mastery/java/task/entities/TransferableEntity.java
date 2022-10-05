package vlad.skiba.mastery.java.task.entities;

import java.util.Objects;

/**
 * Copyright Vlad Skiba (c) 2022.
 */
public class TransferableEntity {

    private Long departmentId;
    private String jobTitle;

    public TransferableEntity() {

    }

    public TransferableEntity(Long departmentId, String jobTitle) {
        this.departmentId = departmentId;
        this.jobTitle = jobTitle;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransferableEntity that = (TransferableEntity) o;
        return Objects.equals(departmentId, that.departmentId) &&
                Objects.equals(jobTitle, that.jobTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentId, jobTitle);
    }

    @Override
    public String toString() {
        return String.format("Department Id: %d\nJob Title: %s\n", departmentId, jobTitle);
    }

}
