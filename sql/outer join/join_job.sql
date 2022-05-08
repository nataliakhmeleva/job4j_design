select * from departments d
left join employees e  
on d.id = e.department_id
where e.department_id is null;

select * from employees e right join departments d on e.department_id = d.id;
select * from departments d  left join employees e on  e.department_id = d.id;
