create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices (name, price) values ('Телефон', 20000),('Наушники', 3000),('Телевизор', 40000),('Ноутбук', 100000);
insert into people (name) values ('Любовь'),('Алексей'),('Марк');
insert into devices_people (device_id, people_id) values (1,1), (1,2), (1,3), (2,1), (2,3), (3,3), (4,1), (4,3);