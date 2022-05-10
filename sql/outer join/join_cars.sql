create table carbody (
	id serial primary key,
    name varchar(255)
);

create table engine (
	id serial primary key,
    name varchar(255)
);

create table transmission (
	id serial primary key,
    name varchar(255)
);

create table cars (
	id serial primary key,
    name varchar(255),
	carbody_id int references carbody(id),
	engine_id int references engine(id),
	transmission_id int references transmission(id)
);

insert into carbody (name) values ('седан'), ('универсал'), ('хетчбек'), ('минивен'), ('кроссовер'), ('купе');
insert into engine (name) values ('бензиновый'), ('дизельный'), ('газовый'), ('электродвигатель');
insert into transmission (name) values ('механическая'), ('автоматическая'), ('полуавтоматическая'), ('роботизированная');

insert into cars (name, carbody_id, engine_id, transmission_id) 
values ('тойота', 1, 1, 2), ('форд', 5, 1, 1),('пежо', 3, 4, 1), ('рено', 2, 2, 1), 
('бмв', 4, 1, 4), ('мерседес', 1, 2, 4), ('лада', 2, 1, 1), ('ниссан', 3, 4, 2);

select c.name as Автомобиль, cb.name as Кузов, 
e.name as Двигатель, t.name as Коробка_передач
from cars c
join carbody cb
on c.carbody_id = cb.id
join engine e
on c.engine_id = e.id
join transmission t
on c.transmission_id = t.id;

select * from carbody cb
left join cars c  
on c.carbody_id = cb.id
where c.carbody_id is null;

select * from engine e
left join cars c  
on c.engine_id = e.id
where c.engine_id is null;

select * from transmission t
left join cars c  
on c.transmission_id = t.id
where c.transmission_id is null;