USE model;


SELECT DB_NAME() AS BaseDeDatosActual;


CREATE TABLE customer (
    id INT IDENTITY(1,1) PRIMARY KEY,
    dni CHAR(8) NOT NULL,
    cellphone CHAR(9) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    state CHAR(1) NOT NULL,
    created_at DATETIME NULL,
    updated_at DATETIME NULL,
    deleted_at DATETIME NULL,
    restored_at DATETIME NULL
);


INSERT INTO customer (dni, cellphone, first_name, last_name, state)
VALUES 
('74564578', '987654321', 'Luis', 'Lopez', 'A'),
('87654321', '912345678', 'María', 'Lopez', 'A'),
('45678912', '999888777', 'Carlos', 'Ramirez', 'A');


SELECT * FROM customer;

