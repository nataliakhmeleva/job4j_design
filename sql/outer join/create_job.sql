create table departments(
	id serial primary key,
    name varchar(255)
);

create table employees(
	id serial primary key,
    name varchar(255),
	department_id int references departments(id)
);

insert into departments(name) values ('ИТ'),('Финансы'),('Бухгалтерия'),
('Снабжение'),('Техническая поддержка');

insert into employees(name, department_id) values ('Иван Филиппов', 1),('Константин Киселев',2),
('Мария Петрова',3),('Анна Иванова',4),('Василий Огурцов',1),('Елена Ветрова',3),('Олег Селов', null),
('Игорь Линь',4),('Ян Абрикосов',2);
