use colegioInterfaz;

INSERT INTO alumno (nombre, apellido, telefono, email, direccion, estado) VALUES
('Juan', 'Pérez', '123456789', 'juan.perez@example.com', 'Calle 1', 'activo'),
('María', 'López', '987654321', 'maria.lopez@example.com', 'Calle 2', 'activo'),
('Carlos', 'Martínez', '456123789', 'carlos.martinez@example.com', 'Calle 3', 'inactivo'),
('Ana', 'Gómez', '789456123', 'ana.gomez@example.com', 'Calle 4', 'activo'),
('Pedro', 'Fernández', '321654987', 'pedro.fernandez@example.com', 'Calle 5', 'activo'),
('Laura', 'Torres', '654321789', 'laura.torres@example.com', 'Calle 6', 'inactivo'),
('Jorge', 'Díaz', '951753852', 'jorge.diaz@example.com', 'Calle 7', 'activo'),
('Sofía', 'Vargas', '852963741', 'sofia.vargas@example.com', 'Calle 8', 'activo'),
('Hugo', 'Rojas', '741852963', 'hugo.rojas@example.com', 'Calle 9', 'activo'),
('Camila', 'Navarro', '369258147', 'camila.navarro@example.com', 'Calle 10', 'activo'),
('Andrés', 'Castro', '123789456', 'andres.castro@example.com', 'Calle 11', 'activo'),
('Elena', 'Mendoza', '321987654', 'elena.mendoza@example.com', 'Calle 12', 'inactivo'),
('Luis', 'Ortega', '258741369', 'luis.ortega@example.com', 'Calle 13', 'activo'),
('Valentina', 'Silva', '147258369', 'valentina.silva@example.com', 'Calle 14', 'activo'),
('Ricardo', 'Morales', '369147258', 'ricardo.morales@example.com', 'Calle 15', 'activo'),
('Daniela', 'Fuentes', '741369852', 'daniela.fuentes@example.com', 'Calle 16', 'activo'),
('Alejandro', 'Herrera', '963258741', 'alejandro.herrera@example.com', 'Calle 17', 'inactivo'),
('Gabriela', 'Cruz', '852147963', 'gabriela.cruz@example.com', 'Calle 18', 'activo'),
('Tomás', 'Reyes', '951852741', 'tomas.reyes@example.com', 'Calle 19', 'activo'),
('Paula', 'Guzmán', '369741258', 'paula.guzman@example.com', 'Calle 20', 'activo'),
('Francisco', 'Ramos', '159753486', 'francisco.ramos@example.com', 'Calle 21', 'activo'),
('Natalia', 'Muñoz', '486357159', 'natalia.munoz@example.com', 'Calle 22', 'inactivo'),
('Esteban', 'Peña', '753159486', 'esteban.pena@example.com', 'Calle 23', 'activo'),
('Clara', 'Salazar', '951357486', 'clara.salazar@example.com', 'Calle 24', 'activo'),
('Felipe', 'Paredes', '357486951', 'felipe.paredes@example.com', 'Calle 25', 'activo'),
('Isabel', 'Saavedra', '654789123', 'isabel.saavedra@example.com', 'Calle 26', 'activo'),
('Manuel', 'Vega', '789123654', 'manuel.vega@example.com', 'Calle 27', 'activo'),
('Rosa', 'Cárdenas', '852963147', 'rosa.cardenas@example.com', 'Calle 28', 'inactivo'),
('Cristian', 'Aguilar', '963147852', 'cristian.aguilar@example.com', 'Calle 29', 'activo'),
('Pilar', 'Montoya', '741963258', 'pilar.montoya@example.com', 'Calle 30', 'activo');

-- Inserciones para la tabla asignatura
INSERT INTO asignatura (nombre) VALUES
('Matemáticas'),
('Física'),
('Química'),
('Historia'),
('Geografía'),
('Literatura'),
('Biología'),
('Educación Física'),
('Arte'),
('Música');

-- Inserciones para la tabla matricula
INSERT INTO matricula (id_alumno, id_asignatura, nota) VALUES
(1, 1, 8.5), (2, 2, 7.3), (3, 3, 6.9), (4, 4, 9.1), (5, 5, 7.8),
(1, 2, 8.0), (2, 3, 7.5), (3, 4, 6.8), (4, 5, 9.0), (5, 1, 7.9),
(1, 3, 8.2), (2, 4, 7.1), (3, 5, 6.5), (4, 1, 9.3), (5, 2, 8.1),
(1, 4, 8.7), (2, 5, 7.2), (3, 1, 6.7), (4, 2, 9.4), (5, 3, 7.6),
(1, 5, 8.4), (2, 1, 7.9), (3, 2, 6.3), (4, 3, 9.2), (5, 4, 7.0),
(1, 2, 8.8), (2, 3, 7.4), (3, 4, 6.6), (4, 5, 9.5), (5, 1, 7.7),
(1, 3, 8.3), (2, 4, 7.0), (3, 5, 6.4), (4, 1, 9.6), (5, 2, 8.0),
(1, 4, 8.6), (2, 5, 7.3), (3, 1, 6.2), (4, 2, 9.1), (5, 3, 7.8),
(1, 5, 8.1), (2, 1, 7.6), (3, 2, 6.9), (4, 3, 9.0), (5, 4, 7.5),
(1, 2, 8.9), (2, 3, 7.2), (3, 4, 6.8), (4, 5, 9.3), (5, 1, 7.1),
(1, 3, 8.5), (2, 4, 7.7), (3, 5, 6.1), (4, 1, 9.2), (5, 2, 8.2),
(1, 4, 8.7), (2, 5, 7.5), (3, 1, 6.3), (4, 2, 9.4), (5, 3, 7.9),
(1, 5, 8.0), (2, 1, 7.8), (3, 2, 6.5), (4, 3, 9.1), (5, 4, 7.6),
(1, 2, 8.4), (2, 3, 7.1), (3, 4, 6.9), (4, 5, 9.5), (5, 1, 7.2),
(1, 3, 8.6), (2, 4, 7.4), (3, 5, 6.7), (4, 1, 9.3), (5, 2, 8.3);