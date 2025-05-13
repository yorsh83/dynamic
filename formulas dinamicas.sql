CREATE TABLE formulas (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,         -- Nombre descriptivo
    expresion TEXT NOT NULL,              -- Ejemplo: "a + b * c"
    descripcion TEXT,                     -- Opcional: para explicar la fórmula
    fecha_creacion TIMESTAMP DEFAULT NOW(),
    activo BOOLEAN DEFAULT TRUE
);

SELECT id, nombre, expresion FROM formulas WHERE activo = true;

INSERT INTO formulas (nombre, expresion, descripcion) VALUES
('Suma y multiplicación', 'a + b * c', 'Suma a con el producto de b y c'),
('Área de rectángulo', 'base * altura', 'Calcula el área de un rectángulo'),
('IVA aplicado', 'precio * 0.16', 'Aplica el 16% de IVA al precio');


