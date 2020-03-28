alter table cleanings rename column estimated_date to next_date;
alter table cleanings rename column realized_date to due_date;
alter table cleanings add column frequency integer not null default 0;


create table cleaning_tasks
(
    id serial primary key,
    ref_cleaning integer not null,
    ref_task integer not null,
    realized boolean not null default false,

    foreign key (ref_cleaning) references cleanings (id),
    foreign key (ref_task) references tasks (id)
);
  

create table cleaning_products
(
    id serial primary key,
    ref_cleaning integer not null,
    ref_product integer not null,
    amount  numeric,
    realized boolean not null default false,

    foreign key (ref_cleaning) references cleanings (id),
    foreign key (ref_product) references products (id)
);


insert into cleanings values ( 1, 'Limpeza Semanal', 'limpar com cuidado', to_date('20200503','YYYYMMDD'), null, '01:00:00', true, 0 );
insert into cleaning_tasks values ( 1, 1, 1, false );
insert into cleaning_products values ( 1, 1, 1, 0, false );