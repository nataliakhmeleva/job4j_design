create table teens (
	 id serial primary key,
    name varchar(255),
	gender varchar(1)
);

insert into teens (name, gender) values ('Маша', 'Ж'), ('Паша', 'М'), ('Ваня', 'М'), 
('Рома', 'М'), ('Ира', 'Ж'), ('Алла', 'Ж'), ('Игорь', 'М'), ('Лиза', 'Ж'); 

select * from teens t1 cross join teens t2 
where t1.gender = 'М' and t2.gender = 'Ж';