create table tasks 
(
    id          serial       primary key,
    name        varchar(160) not null,
    guidelines  text         null,
    state       boolean      not null default true
);