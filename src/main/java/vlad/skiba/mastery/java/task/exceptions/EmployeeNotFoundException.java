package vlad.skiba.mastery.java.task.exceptions;

/**
 * Copyright Vlad Skiba (c) 2022.
 */
public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException() {
        super();
    }

    public EmployeeNotFoundException(String message) {
        super(message);
    }

    public EmployeeNotFoundException(Throwable cause) {
        super(cause);
    }

    public EmployeeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
