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

select * from employees e  left join departments d on  e.department_id = d.id;
select * from employees e right join departments d on d.id = e.department_id;
select * from employees e  full join departments d on  e.department_id = d.id;
select * from employees e  cross join departments d;

select * from departments d
left join employees e  
on d.id = e.department_id
where e.department_id is null;

select e.name as Сотрудник, d.name as Депортамент 
from employees e  left join departments d 
on  e.department_id = d.id;

select e.name as Сотрудник, d.name as Депортамент 
from departments d  right join  employees e
on  e.department_id = d.id;
