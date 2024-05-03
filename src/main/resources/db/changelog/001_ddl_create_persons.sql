create table if not exists persons (
    id             serial primary key,
    name           varchar(255)             NOT NULL,
    login          varchar(255)             NOT NULL,
    password       varchar(255)             NOT NULL,
    UNIQUE (login)
);