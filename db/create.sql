CREATE TABLE IF NOT EXISTS cars (
    id serial primary key,
    model varchar(255),
    manufacturer varchar(255),
    year_of_issue int,
    engine_id int NOT NULL REFERENCES engines(id)
);

CREATE TABLE IF NOT EXISTS engines (
    id serial primary key,
    model varchar(255),
    manufacturer varchar(255),
    year_of_issue int
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