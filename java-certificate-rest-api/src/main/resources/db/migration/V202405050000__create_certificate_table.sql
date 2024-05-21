CREATE TABLE certificate
(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    employee_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    grade VARCHAR(255) NOT NULL,
    score FLOAT NULL,
    expired_date DATE NOT NULL
);