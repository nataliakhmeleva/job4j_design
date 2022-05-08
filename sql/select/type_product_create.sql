create table type(
	id serial primary key,
    name varchar(255)
);

create table product(
	id serial primary key,
    name varchar(255),
    price float,
	expired_date date,
	type_id int references type(id)
);

insert into type(name) values ('СЫР'),('МОЛОКО'),('ХЛЕБ'),('КОЛБАСА');
insert into product(name, price, expired_date, type_id)
values ('Моцарелла', 350, '2022-05-22', 1),('Пармезан', 752, '2022-05-01', 1),
('Гауда', 632, '2022-06-17', 1),('Масдаам', 1020, '2022-07-14', 1);
insert into product(name, price, expired_date, type_id)
values ('Молоко топленое', 80, '2022-05-04', 2),('Молоко 3,2%', 68, '2022-05-12', 2),
('Мороженое', 100, '2022-06-18', 2);
insert into product(name, price, expired_date, type_id)
values ('Хлеб пшеничный', 50, '2022-05-10', 3),('Хлеб бородинский', 55, '2022-05-01', 3),
('Батон нарезной', 45, '2022-05-02', 3),('Плюшка московская', 50, '2022-05-14', 3);
insert into product(name, price, expired_date, type_id)
values ('Колбаса докторская', 602, '2022-07-10', 4),('Сервелат', 841, '2022-08-08', 4);