DROP SCHEMA public CASCADE;
CREATE SCHEMA IF NOT EXISTS public;

-- таблица аккаунтов
CREATE TABLE IF NOT EXISTS account
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY,    -- идентификатор таблицы
    name        VARCHAR(30),                            -- имя аккаунта
    username    VARCHAR(30),                            -- логин
    password    VARCHAR(30),                            -- пароль
    role        VARCHAR(30)                             -- тип аккаунта
);

-- таблица покупателей
CREATE TABLE IF NOT EXISTS customer
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY,    -- идентификатор таблицы
    id_account  BIGINT,                                 -- связь с таблицей аккаунтов
    name        VARCHAR(30),                            -- имя
    surname     VARCHAR(30),                            -- фамилия
    email       VARCHAR(30),                            -- почта
    phone       VARCHAR(30),                            -- телефон
    address     VARCHAR(255)                            -- адрес
);

-- таблица магазинов
CREATE TABLE IF NOT EXISTS shop
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY,    -- идентификатор таблицы
    id_account  BIGINT,                                 -- связь с таблицей аккаунтов
    name        VARCHAR(255),                           -- название магазина
    unp         INTEGER,                                -- УНП
    phone       VARCHAR(20),                            -- телефон
    address     VARCHAR(255)                            -- адрес
);

-- таблица типов товаров
CREATE TABLE IF NOT EXISTS product_type
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY,    -- идентификатор таблицы
    name_type   VARCHAR(255),                           -- тип товара
    name_table  VARCHAR(255)                            -- генерируемое название таблицы для типа товара
);

-- таблица товаров
CREATE TABLE IF NOT EXISTS product
(
    id                  BIGINT GENERATED ALWAYS AS IDENTITY,    -- идентификатор таблицы
    id_product_type     BIGINT,                                 -- связь с таблицей типов товаров
    name                VARCHAR(255)                            -- название товара
);

-- таблица фото товаров
CREATE TABLE IF NOT EXISTS photo
(
    id                  BIGINT GENERATED ALWAYS AS IDENTITY,    -- идентификатор таблицы
    id_product          BIGINT,                                 -- связь с таблицей товаров
    photo               VARCHAR(255)                            -- ссылка на фото товара
);

-- таблица для связи магазинов и товаров
CREATE TABLE IF NOT EXISTS shop_product
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY,    -- идентификатор таблицы
    id_shop     BIGINT,                                 -- связь с таблицей магазинов
    id_product  BIGINT,                                 -- связь с таблицей товаров
    price       DECIMAL,                                -- цена товара
    quantity    INTEGER,                                -- количество товара в наличии
    delivery    VARCHAR(50)                             -- срок доставки
);

-- таблица для корзины
CREATE TABLE IF NOT EXISTS cart
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY,    -- идентификатор таблицы
    id_customer BIGINT,                                 -- связь с таблицей покупателей
    status      VARCHAR(30)                             -- связь с таблицей магазинов
);

-- таблица для товаров в корзине
CREATE TABLE IF NOT EXISTS cart_item
(
    id              BIGINT GENERATED ALWAYS AS IDENTITY,    -- идентификатор таблицы
    id_cart         BIGINT,                                 -- связь с корзиной
    id_shop_product BIGINT,                                 -- связь с товаром продавца
    quantity        INTEGER                                 -- количество товара в заказе
);

INSERT INTO account (name, username, password, role)
VALUES ('admin', 'admin', 'admin', 'admin'),
       ('user', 'user', 'user', 'user'),
       ('business', 'business', 'business', 'business'),
       ('business2', 'business2', 'business2', 'business');

INSERT INTO customer (id_account, name, surname, email, phone, address)
VALUES ('1', 'Иван', 'Иванов', 'ivan@host.com', '+375291111111', 'Брест'),
       ('2', 'Петр', 'Петров', 'petr@host.com', '+375292222222', 'Витебск'),
       ('3', 'Сидор', 'Сидоров', 'sidor@host.com', '+375293333333', 'Гомель'),
       ('4', 'Василий', 'Васин', 'vasin@host.com', '+375294444444', 'Гродно');


INSERT INTO shop (id_account, name, unp, phone, address)
VALUES ('3', 'Рога и Копыта', '123456789', '+375295555555', 'Брест'),
       ('3', 'Из-под полы', '987654321', '+375296666666', 'Витебск'),
       ('4', 'Топ за свои деньги', '111111111', '+375297777777', 'Минск');


INSERT INTO product_type (name_type, name_table)
VALUES ('Телевизоры', 'televizori'),
       ('Мобильные телефоны',  'mobilnie_telefoni');

-- таблица столбцов типов товаров
CREATE TABLE IF NOT EXISTS product_type_column
(
    id                  BIGINT GENERATED ALWAYS AS IDENTITY,    -- идентификатор таблицы
    id_product_type     BIGINT,                                 -- связь с таблицей типов товаров
    column_name_front   VARCHAR(255),                           -- название параметра
    column_name         VARCHAR(255)                            -- генерируемое название столбца для параметра
);

INSERT INTO product_type_column (id_product_type, column_name_front, column_name)
VALUES ('1', 'Производитель', 'proizvoditel'),
       ('1', 'Диагональ', 'diagonal'),
       ('1', 'Разрешение', 'razreshenie'),
       ('1', 'Тип матрицы', 'tip_matrici'),
       ('1', 'Цвет', 'cvet'),
       ('2', 'Производитель', 'proizvoditel'),
       ('2', 'Оперативная память', 'operativnaya_pamyat'),
       ('2', 'Встроенная память', 'vstroennaya_pamyat'),
       ('2', 'Размер экрана', 'razmer_ekrana'),
       ('2', 'Емкость аккумулятора', 'emkost_akkumulyatora'),
       ('2', 'Цвет', 'cvet');

-- таблица параметров телевизоров
CREATE TABLE IF NOT EXISTS televizori
(
    id                  BIGINT GENERATED ALWAYS AS IDENTITY,    -- идентификатор таблицы
    id_product_type     BIGINT,                                 -- связь с таблицей типов товаров
    id_product          BIGINT,
    proizvoditel        VARCHAR(255),
    diagonal            VARCHAR(255),
    razreshenie         VARCHAR(255),
    tip_matrici         VARCHAR(255),
    cvet                VARCHAR(255)
);

-- таблица параметров мобильных телефонов
CREATE TABLE IF NOT EXISTS mobilnie_telefoni
(
    id                      BIGINT GENERATED ALWAYS AS IDENTITY,    -- идентификатор таблицы
    id_product_type         BIGINT,                                 -- связь с таблицей типов товаров
    id_product              BIGINT,
    proizvoditel            VARCHAR(255),
    operativnaya_pamyat     VARCHAR(255),
    vstroennaya_pamyat      VARCHAR(255),
    razmer_ekrana           VARCHAR(255),
    emkost_akkumulyatora    VARCHAR(255),
    cvet                    VARCHAR(255)
);

INSERT INTO televizori (id_product_type, id_product, proizvoditel, diagonal, razreshenie, tip_matrici, cvet)
VALUES ('1', '1', 'LG', '32', '1920x1080', 'IPS', 'Черный'),
       ('1', '2', 'Samsung', '32', '1920x1080', 'VA', 'Черный'),
       ('1', '3', 'LG', '55', '3840x2160', 'IPS', 'Белый');

INSERT INTO mobilnie_telefoni (id_product_type, id_product, proizvoditel, operativnaya_pamyat, vstroennaya_pamyat, razmer_ekrana, emkost_akkumulyatora, cvet)
VALUES ('2', '4', 'Apple', '6', '128', '6.1', '3349', 'Белый'),
       ('2', '5', 'Xiaomi', '8', '256', '6.67', '5000', 'Зеленый'),
       ('2', '6', 'Xiaomi', '8', '512', '6.67', '5000', 'Черный');

INSERT INTO product (id_product_type, name)
VALUES ('1', 'Телевизор LG 32 IPS'),
       ('1', 'Телевизор Samsung 32 VA'),
       ('1', 'Телевизор LG 55 IPS'),
       ('2', 'Смартфон Apple iPhone 15 128GB'),
       ('2', 'Смартфон Xiaomi Redmi Note 13 8GB/256GB'),
       ('2', 'Смартфон Xiaomi Redmi Note 13 8GB/512GB');

INSERT INTO shop_product (id_shop, id_product, price, quantity, delivery)
VALUES ('1', '1', '2000', '5', '5'),
       ('1', '4', '3000', '5', '5'),
       ('2', '1', '2500', '10', '6'),
       ('3', '1', '2200', '4', '4'),
       ('2', '2', '2300', '5', '3'),
       ('2', '3', '2400', '5', '4'),
       ('3', '4', '2500', '5', '6'),
       ('3', '5', '2800', '5', '7');