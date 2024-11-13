INSERT INTO account (username, password, role)
VALUES ('admin', 'admin', 'ADMIN'),
       ('user', 'user', 'USER'),
       ('business', 'business', 'BUSINESS'),
       ('business2', 'business2', 'BUSINESS');

INSERT INTO customer (id_account, first_name, last_name, phone, address)
VALUES ('1', 'Иван', 'Иванов', '+375291111111', 'Брест'),
       ('2', 'Петр', 'Петров', '+375292222222', 'Витебск'),
       ('3', 'Сидор', 'Сидоров', '+375293333333', 'Гомель'),
       ('4', 'Василий', 'Васин', '+375294444444', 'Гродно');

INSERT INTO shop (id_account, name)
VALUES ('3', 'Рога и Копыта'),
       ('3', 'Из-под полы'),
       ('4', 'Топ за свои деньги');

INSERT INTO product_type (type_name, photo)
VALUES ('Телевизоры', 'https://imarket.by/upload/medialibrary/b69/b690705cdc8dfba5d1c32b46eab90aaa.png'),
       ('Мобильные телефоны', 'https://m.media-amazon.com/images/I/71BcNVNnJDL.jpg');

INSERT INTO product (id_product_type, name, description)
VALUES ('1', 'Телевизор LG 32 IPS', 'Телевизор LG 32 IPS'),
       ('1', 'Телевизор Samsung 32 VA', 'Телевизор Samsung 32 VA'),
       ('1', 'Телевизор LG 55 IPS', 'Телевизор LG 55 IPS'),
       ('2', 'Смартфон Apple iPhone 15 128GB', 'Смартфон Apple iPhone 15 128GB'),
       ('2', 'Смартфон Xiaomi Redmi Note 13 8GB/256GB', 'Смартфон Xiaomi Redmi Note 13 8GB/256GB'),
       ('2', 'Смартфон Xiaomi Redmi Note 13 8GB/512GB', 'Смартфон Xiaomi Redmi Note 13 8GB/512GB');

INSERT INTO shop_product (id_shop, id_product, price, delivery)
VALUES ('1', '1', '2000', '5 дней'),
       ('1', '4', '3000', '5 дней'),
       ('2', '1', '2500', '6 дней'),
       ('3', '1', '2200', '4 дня'),
       ('2', '2', '2300', '3 дня'),
       ('2', '3', '2400', '4 дня'),
       ('3', '4', '2500', '6 дней'),
       ('3', '5', '2800', '7 дней');

