CREATE TABLE employee
(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    employee_type VARCHAR(255) NOT NULL,
    dob DATE NULL
);

CREATE TABLE certificate
(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    employee_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    grade VARCHAR(255) NOT NULL,
    score FLOAT NULL,
    expired_date DATE NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employee(id) ON DELETE CASCADE
);