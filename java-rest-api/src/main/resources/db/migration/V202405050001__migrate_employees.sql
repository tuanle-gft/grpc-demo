-- EMPLOYEE
INSERT INTO employee(employee_type, dob, name)
VALUES("FULL_TIME", '1991-01-01', 'Employee 01');

INSERT INTO employee(employee_type, dob, name)
VALUES("PART_TIME", '1992-01-01', 'Employee 02');

INSERT INTO employee(employee_type, dob, name)
VALUES("FULL_TIME", '1993-01-01', 'Employee 03');

INSERT INTO employee(employee_type, dob, name)
VALUES("PART_TIME", NULL, 'Employee 04');

-- CERTIFICATE
INSERT INTO certificate(employee_id, name, grade, score, expired_date)
VALUES(1, 'Go', 'Good', NULL, '2025-01-01');

INSERT INTO certificate(employee_id, name, grade, score, expired_date)
VALUES(1, 'Java', 'Excellent', 9.5, '2030-01-01');

INSERT INTO certificate(employee_id, name, grade, score, expired_date)
VALUES(2, 'C#', 'Good', NULL, '2026-01-01');