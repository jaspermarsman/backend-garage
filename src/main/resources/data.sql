INSERT INTO users (username, password, enabled, email)
VALUES
    ('mechanic', '$2y$10$Asnk2HVhhHTwlOur0beC8.SQSHd.zOCKidUPIJYC4xSKy/CAxToQy', TRUE, 'mechanic@gmail.com'),
    ('backoffice', '$2y$10$Asnk2HVhhHTwlOur0beC8.SQSHd.zOCKidUPIJYC4xSKy/CAxToQy', TRUE, 'backoffice@gmail.com'),
    ('admin', '$2y$10$Asnk2HVhhHTwlOur0beC8.SQSHd.zOCKidUPIJYC4xSKy/CAxToQy', TRUE, 'admin@gmail.com');

INSERT INTO authorities (username, authority)
VALUES
('mechanic', 'ROLE_MECHANIC'),
('admin', 'ROLE_USER'),
('admin', 'ROLE_ADMIN' ),
('backoffice', 'ROLE_BACKOFFICE');

INSERT INTO customers (first_name, second_name)
VALUES
('Jasper', 'Marsman'),
('Gerard', 'Koenen');

INSERT INTO cars (brand, model, license_plate, customer_id)
values
('Skoda', 'Fabia', '64-PKG-5', '1'),
('Fiat', 'Punto', 'XG-28-GJ', '2');

INSERT INTO car_parts (description, price, amount_in_stock)
values
('exhaust', '100', '5'),
('brake-disc', '50', '4'),
('clutch-plate', '80', '8');

INSERT INTO reparations (appointment_date, car_id)
values
('2022-02-02', '1'),
('2022-02-03', '2');

INSERT INTO used_car_parts (car_parts_id, reparation_id)
values
('1', '1'),
('2', '2');