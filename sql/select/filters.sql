select  * from product 
where type_id = (select id from type where name = 'СЫР');

select * from product
where name like '%Мороженое%';

select * from product
where expired_date < (select current_date);

select * from product order by price desc limit 1;

select t.name, count(p.name)
from product p
join type as t
on p.type_id = t.id
group by t.name;

select  p.* from product as p
join type as t
on p.type_id = t.id
where t.name = 'СЫР' or t.name = 'МОЛОКО';

select t.name, count(p.name)
from product p
join type as t
on p.type_id = t.id
group by t.name
having count(p.name) < 10;

select t.name, p.* from product as p
join type as t
on p.type_id = t.id;