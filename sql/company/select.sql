CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company (id, name) values (1, 'Yandex'), (2, 'Google'),(3, 'Amazon'),(4, 'Meta'),(5, 'Apple'),(6, 'Yahoo');

insert into person (id, name, company_id) values 
    (1, 'Ivan', 1), (2, 'Igor', 1), (3, 'Anna', 1), (4, 'Varvara', 1),
    (5, 'Den', 2), (6, 'Stiven', 2), (7, 'Alex', 2), (8, 'Kate', 2),
    (9, 'Rich', 3), (10, 'Sofia', 3), (11, 'Karla', 3), (12, 'Meggan', 3),
    (13, 'Krister', 4), (14, 'Mila', 4), (15, 'Max', 4), (16, 'Marta', 4),
    (17, 'Oliver', 5), (18, 'Li', 5), (19, 'TJ', 5), (20, 'Ostin', 5), (21, 'Liza', 5),
    (22, 'Mark', 6), (23, 'Loya', 6), (24, 'Join', 6), (25, 'Kolin', 6), (26, 'Yang', 6);
    
select p.company_id , p.name name_person, c.name name_company 
from person p
join company c
on p.company_id = c.id
where p.company_id != 5;

select c.name, count(p.company_id)  
from company c
join person p
on p.company_id = c.id
group by c.name
having count(p.company_id) = (
    select count(company_id) 
    from person  
    group by company_id 
    order by count(company_id) desc
    limit 1)
order by c.name;