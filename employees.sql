-- Drop table if exists.
DROP TABLE IF EXISTS employees;


-- Create employee table
CREATE TABLE employees (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  first_name VARCHAR(45),
  last_name VARCHAR(45),
  email VARCHAR(45),
  age INT
);

-- Inserting data

INSERT INTO employees (first_name, last_name, email, age)
VALUES
('Leslie', 'Andrews', 'leslie@gmail.com', 23),
('Emma', 'Baumgarten', 'emma@gmail.com', 41),
('Yuri', 'Petrov', 'yuri@gmail.com', 19),
('Juan', 'Vega', 'juan@gmail.com', 32);

SELECT * FROM employees;