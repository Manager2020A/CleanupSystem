create table products 
(
    id          serial       primary key,
    name        varchar(160) not null,
    branding    varchar(160) null,
    capacity    numeric      null,
    active      boolean      not null default true
);

alter table tasks rename column state to active;