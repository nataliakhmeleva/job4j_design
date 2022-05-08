select  * from product 
where type_id = (select id from type where name = 'СЫР');

select * from product
where name like '%Мороженое%';

select * from product
where expired_date < (select current_date);

select  * from product 
where price = (select max(price) from product);

select t.name, count(p.name)
from product p
join type as t
on p.type_id = t.id
group by t.name;

select  * from product 
join type as t
on product.type_id = t.id
where t.name = 'СЫР' or t.name = 'МОЛОКО';

select t.name, count(p.name)
from product p
join type as t
on p.type_id = t.id
group by t.name
having count(p.name) < 10;

select t.name, product.* from product as product
join type as t
on product.type_id = t.id;