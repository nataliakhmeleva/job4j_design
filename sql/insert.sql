insert into roles(name) values('seller');
insert into roles(name) values('customer');

insert into rules(name) values('sell');
insert into rules(name) values('advise');
insert into rules(name) values('pay');

insert into roles_rules(roles_id, rules_id) values('1', '1');
insert into roles_rules(roles_id, rules_id) values('1', '2');
insert into roles_rules(roles_id, rules_id) values('2', '3');

insert into users(name, roles_id) values('Ivan', '1');
insert into users(name, roles_id) values('Kirill', '2');
insert into users(name, roles_id) values('Maria', '2');

insert into category(name) values('books');
insert into category(name) values('magazines');

insert into states(name) values('in stock');
insert into states(name) values('under the order');
insert into states(name) values('absence');

insert into item(name, users_id, category_id, states_id) values('sellind', '1', '1', '1');
insert into item(name, users_id, category_id, states_id) values('shopping', '2', '1', '2');
insert into item(name, users_id, category_id, states_id) values('waiting', '3', '2', '3');

insert into commentses(name, item_id) values('Its good!', '1');
insert into commentses(name, item_id) values('I did not like the quality of the paper', '2');
insert into commentses(name, item_id) values('Long wait!', '3');

insert into commentses(name, item_id) values('fale1.jpg', '1');