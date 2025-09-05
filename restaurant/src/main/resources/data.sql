INSERT INTO category (name, created_at) VALUES
('ENTRADAS',        '2025-01-01T12:00:00'),
('PLATOS FUERTES',  '2025-01-02T12:00:00'),
('POSTRES',         '2025-01-03T12:00:00'),
('BEBIDAS',         '2025-01-04T12:00:00'),
('ESPECIALIDADES',  '2025-01-05T12:00:00');

INSERT INTO dish (id, name, description, price, category, created_at, updated_at) VALUES
('550e8400-e29b-41d4-a716-446655440000', 'Pizza Margarita', 'Pizza clásica con salsa de tomate, mozzarella y albahaca', 8.99, 'PLATOS FUERTES', '2025-01-10T12:00:00', '2025-01-10T12:00:00'),
('550e8400-e29b-41d4-a716-446655440001', 'Ensalada César', 'Lechuga romana, pollo, crutones y aderezo César', 6.50, 'ENTRADAS',       '2025-01-11T12:00:00', '2025-01-11T12:00:00'),
('550e8400-e29b-41d4-a716-446655440002', 'Spaghetti Bolognese', 'Pasta con salsa boloñesa de carne', 10.00, 'PLATOS FUERTES', '2025-01-12T12:00:00', '2025-01-12T12:00:00'),
('550e8400-e29b-41d4-a716-446655440003', 'Cheesecake', 'Tarta de queso con base de galleta y mermelada de frutos rojos', 5.75, 'POSTRES', '2025-01-13T12:00:00', '2025-01-13T12:00:00'),
('550e8400-e29b-41d4-a716-446655440004', 'Jugo de Naranja', 'Jugo natural de naranja recién exprimido', 3.20, 'BEBIDAS', '2025-01-14T12:00:00', '2025-01-14T12:00:00');
