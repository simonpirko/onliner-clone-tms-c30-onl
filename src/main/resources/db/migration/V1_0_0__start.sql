-- таблица аккаунтов
CREATE TABLE IF NOT EXISTS account
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY,    -- идентификатор таблицы
    username    VARCHAR(30) UNIQUE,                     -- логин
    password    VARCHAR(30),                            -- пароль
    role        VARCHAR(30)                             -- тип аккаунта
    );