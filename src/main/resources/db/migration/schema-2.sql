create table products 
(
    id          serial       primary key,
    name        varchar(160) not null,
    branding    varchar(160) null,
    capacity    numeric      null,
    active      boolean      not null default true
);

alter table tasks rename column state to active;

insert into products ( name, capacity ) values ( 'Álcool', 500 );
insert into products ( name, capacity ) values ( 'Álcool gel', 500 );
insert into products ( name, capacity ) values ( 'Detergente', 500 );
insert into products ( name, capacity ) values ( 'Desengordurante', 500 );
insert into products ( name, capacity ) values ( 'Limpa-vidros', 500 );
insert into products ( name, capacity ) values ( 'Sabão', 1000 );
insert into products ( name, capacity ) values ( 'Amaciante', 1000 );