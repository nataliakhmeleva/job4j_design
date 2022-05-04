create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);
insert into fauna(name, avg_age, discovery_date) values ('cat', '3650', '1120-06-10');
insert into fauna(name, avg_age, discovery_date) values ('dog', '4850', '1370-02-22');
insert into fauna(name, avg_age, discovery_date) values ('bee', '7820', '1830-11-10');
insert into fauna(name, avg_age, discovery_date) values ('spider', '10650', '1951-10-16');
insert into fauna(name, avg_age, discovery_date) values ('tiger', '12000', '1891-11-01');
insert into fauna(name, avg_age) values ('elefant', '6524');
insert into fauna(name, avg_age, discovery_date) values ('sneak', '13650', '1960-02-10');
insert into fauna(name, avg_age, discovery_date) values ('sheep', '8650', '1766-05-28');
insert into fauna(name, avg_age, discovery_date) values ('fish', '1250', '1313-02-25');
insert into fauna(name, avg_age) values ('bird', '10150');