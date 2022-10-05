DROP DATABASE IF EXISTS employee_db;

DROP SCHEMA IF EXISTS employee_db;

CREATE DATABASE IF NOT EXISTS employee_db;

USE employee_db;

CREATE TABLE IF NOT EXISTS employees (
    employee_id     INTEGER(10)     NOT NULL   AUTO_INCREMENT,
    first_name      VARCHAR(255)    NOT NULL,
    last_name       VARCHAR(255)    NOT NULL,
    department_id   INTEGER(10)     NOT NULL,
    job_title       VARCHAR(255)    NOT NULL,
    gender          VARCHAR(255)    NOT NULL,
    date_of_birth   VARCHAR(255)    NOT NULL,
    PRIMARY KEY (employee_id)
);

INSERT INTO employees (first_name, last_name, department_id, job_title, gender, date_of_birth)
VALUES
('Alex', 'Berk', 1, 'Java Developer', 'MALE', '02.02.1984'),
('Andrew', 'Dart', 1, 'Java Developer', 'MALE', '13.09.1995'),
('Kate', 'Edison', 2, '.NET Developer', 'FEMALE', '01.07.2000'),
('Olesya', 'Kravc', 3, 'Back-End Developer', 'FEMALE', '02.09.1989'),
('Alexander', 'Vintik', 4, 'Front-End Developer', 'MALE', '02.02.1984'),
('Dmitry', 'Kycher', 5, 'Full-Stack Developer', 'MALE', '02.02.1984'),
('Kseniya', 'Chyruk', 6, 'HR-manager', 'FEMALE', '01.01.1993'),
('Alex', 'Klimchuk', 4, 'Full-Stack Developer', 'MALE', '06.02.1998'),
('Maxim', 'Proton', 7, 'Project-manager', 'MALE', '10.10.1990');
