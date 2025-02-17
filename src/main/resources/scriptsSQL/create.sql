CREATE DATABASE colegioInterfaz;
USE colegioInterfaz;

CREATE TABLE alumno (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    telefono VARCHAR(20),
    email VARCHAR(150) UNIQUE,
    direccion VARCHAR(255) NOT NULL,
    fecha DATE NOT NULL,
    carnet BOOLEAN NOT NULL,
    estado ENUM('activo', 'inactivo') NOT NULL
);

CREATE TABLE asignatura (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE matricula (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_alumno INT NOT NULL,
    id_asignatura INT NOT NULL,
    nota DOUBLE,
    FOREIGN KEY (id_alumno) REFERENCES alumno(ID) ON DELETE CASCADE,
    FOREIGN KEY (id_asignatura) REFERENCES asignatura(id) ON DELETE CASCADE
);