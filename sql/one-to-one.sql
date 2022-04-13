create table company(
	id serial primary key,
	name varchar(255)
);

create table boss(
	id serial primary key,
	name varchar(255)
);

create table company_boss(
	id serial primary key,
	company_id int references company(id) unique,
	boss_id int references boss(id) unique
);