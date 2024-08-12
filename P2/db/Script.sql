-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS soa_database;

-- Usar la base de datos recién creada
USE soa_database;

-- Crear la tabla 'appointments'
CREATE TABLE IF NOT EXISTS appointments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    patient_id VARCHAR(255) NOT NULL,
    date VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    rol VARCHAR(50) NOT NULL
);

SELECT * FROM appointments;
SELECT * FROM users;