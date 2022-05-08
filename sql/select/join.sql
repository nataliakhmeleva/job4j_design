create table mother(
	id serial primary key,
	name varchar(255)
);

create table children(
	id serial primary key,
	name varchar(255),
	age int,
	sex varchar(10),
	schoolchild boolean,
	mother_id int references mother(id)
); 

insert into mother(name) values ('Inna');
insert into mother(name) values ('Tamara');
insert into mother(name) values ('Ekaterina');

insert into children(name, age, sex, schoolchild, mother_id)
values ('Pavel', 15, 'male', true, 1);
insert into children(name, age, sex, schoolchild, mother_id)
values ('Igor', 3, 'male', false, 1);
insert into children(name, age, sex, schoolchild, mother_id)
values ('Eva', 12, 'female', true, 2);
insert into children(name, age, sex, schoolchild, mother_id)
values ('Olga', 19, 'female', false, 3);
insert into children(name, age, sex, schoolchild, mother_id)
values ('Lev', 5, 'male', false, 3);
insert into children(name, age, sex, schoolchild, mother_id)
values ('Larisa', 14, 'female', true, 3);

select m.name, ch.name, ch.age, ch.sex, ch.schoolchild
from mother as m
join children as ch
on ch.mother_id = m.id;

select m.name as Имя_мамы, ch.name as Имя_ребенка, ch.age as Возраст, 
ch.sex as Пол, ch.schoolchild as Школьник
from mother as m
join children as ch
on ch.mother_id = m.id;

select m.name as Имя_мамы, ch.name as Имя_ребенка, 
ch.schoolchild as Школьник
from mother as m
join children as ch
on ch.mother_id = m.id
where ch.schoolchild = true;