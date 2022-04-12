create table shopping(
	id serial primary key,
	name varchar(255),
	country text,
	availability boolean,
	amount smallint
);

insert into shopping(name, country, availability,amount) values('pen', 'USA', true, 15);

select * from shopping;

update shopping set name = 'pensil';

select * from shopping;

delete from shopping;

select * from shopping;