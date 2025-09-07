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

-- =======================
-- Órdenes de ejemplo
-- =======================
INSERT INTO orders (
    id, description, location_id, id_client, status,
    sub_total, discount, tax, total,
    created_at, updated_at, user_employee_id
) VALUES
-- Orden 1 en Sucursal Norte con cliente Juan Pérez (1667958K)
('11111111-1111-1111-1111-111111111111',
 'Orden de prueba - Mesa 1',
 '123e4567-e89b-12d3-a456-426614174001',
 '1667958K',
 'IN_PROGRESS',
 22.49, 0.00, 2.70, 25.19,
 '2025-09-05 12:00:00', '2025-09-05 12:00:00',
 '44f7dc96-003a-4bb3-8c4d-dfbf04c42720'),

-- Orden 2 en Sucursal Sur con cliente María López (1234567A)
('22222222-2222-2222-2222-222222222222',
 'Orden de prueba - Mesa 2',
 '123e4567-e89b-12d3-a456-426614174002',
 '1234567A',
 'IN_PROGRESS',
 18.99, 1.00, 2.15, 20.14,
 '2025-09-05 13:00:00', '2025-09-05 13:30:00',
 '44f7dc96-003a-4bb3-8c4d-dfbf04c42721');

-- =======================
-- Ítems de ejemplo
-- =======================
INSERT INTO item (
    id, dish_id, dish_name, quantity, unit_price, line_total, order_id
) VALUES
-- Items para Orden 1
('aaaa1111-aaaa-aaaa-aaaa-aaaaaaaaaaaa',
 '550e8400-e29b-41d4-a716-446655440000',
 'Pizza Margarita',
 2, 8.99, 17.98,
 '11111111-1111-1111-1111-111111111111'),

('aaaa2222-aaaa-aaaa-aaaa-aaaaaaaaaaaa',
 '550e8400-e29b-41d4-a716-446655440001',
 'Ensalada César',
 1, 4.50, 4.50,
 '11111111-1111-1111-1111-111111111111'),

-- Items para Orden 2
('bbbb1111-bbbb-bbbb-bbbb-bbbbbbbbbbbb',
 '550e8400-e29b-41d4-a716-446655440002',
 'Spaghetti Bolognese',
 1, 10.00, 10.00,
 '22222222-2222-2222-2222-222222222222'),

('bbbb2222-bbbb-bbbb-bbbb-bbbbbbbbbbbb',
 '550e8400-e29b-41d4-a716-446655440004',
 'Jugo de Naranja',
 3, 3.00, 9.00,
 '22222222-2222-2222-2222-222222222222');