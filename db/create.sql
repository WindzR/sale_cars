CREATE TABLE IF NOT EXISTS cars (
    id serial primary key,
    model varchar(255),
    manufacturer varchar(255),
    year_of_issue int,
    engine_id int NOT NULL REFERENCES engines(id),
    car_body_type_id int NOT NULL REFERENCES car_body_types(id)
);

CREATE TABLE IF NOT EXISTS engines (
    id serial primary key,
    model varchar(255),
    manufacturer varchar(255),
    year_of_issue int
);

CREATE TABLE IF NOT EXISTS car_body_types (
    id serial primary key,
    model varchar(255)
);

CREATE TABLE IF NOT EXISTS drivers (
    id serial primary key,
    name varchar(255),
    driver_license int
);

CREATE TABLE IF NOT EXISTS history_owner (
    id serial primary key,
    driver_id int not null references drivers(id),
    car_id int not null references cars(id)
);

CREATE TABLE IF NOT EXISTS announcement (
    id serial primary key,
    created timestamp,
    price decimal ,
    description text,
    is_sold boolean,
    user_id NOT NULL REFERENCES users(id),
    city_id NOT NULL REFERENCES cities(id),
    image_id NOT NULL REFERENCES images(id)
);

CREATE TABLE IF NOT EXISTS users (
    id serial primary key,
    name varchar(50),
    email varchar(50) UNIQUE,
    password varchar(30),
    phone varchar(20),
    role_id NOT NULL REFERENCES roles(id)
);

CREATE TABLE IF NOT EXISTS roles (
    id serial primary key,
    role varchar(255)
);

CREATE TABLE IF NOT EXISTS cities (
    id serial primary key,
    name varchar(255)
);

CREATE TABLE IF NOT EXISTS images (
    id serial primary key,
    link text
);