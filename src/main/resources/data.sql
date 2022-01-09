INSERT INTO users (username, password, enabled, email)
VALUES
    ('user', '$2y$10$Asnk2HVhhHTwlOur0beC8.SQSHd.zOCKidUPIJYC4xSKy/CAxToQy', TRUE, 'user@gmail.com'),
    ('admin', '$2y$10$Asnk2HVhhHTwlOur0beC8.SQSHd.zOCKidUPIJYC4xSKy/CAxToQy', TRUE, 'admin@gmail.com');

INSERT INTO authorities (username, authority)
VALUES
('user', 'ROLE_USER'),
('admin', 'ROLE_USER'),
('admin', 'ROLE_ADMIN' );

INSERT INTO customers (first_name, second_name)
VALUES
('Jasper', 'Marsman');

INSERT INTO cars (brand, model, license_plate, customer_id)
values
('Skoda', 'Fabia', '64-PKG-5', '1');

INSERT INTO car_parts (description, price, amount_in_stock)
values
('exhaust', '100', '5'),
('brake-disc', '50', '4'),
('clutch-plate', '80', '8');

INSERT INTO reparations (appointment_date, car_id)
values
('2022-02-02', '1');