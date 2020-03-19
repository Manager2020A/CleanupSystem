create table cleanings 
(
    id              serial       primary key,
    name            varchar(160) not null,
    guidelines      text         null,
    estimated_date  date         not null,
    realized_date   date         null,
    estimated_time  time         not null,
    active          boolean      not null default true
);