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

INSERT INTO cars (brand, model, model_year, license_plate, customer_id)
values
('Skoda', 'Fabia', '2011', '64-PKG-5', '1');

