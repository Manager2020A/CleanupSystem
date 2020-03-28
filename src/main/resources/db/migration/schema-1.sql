create table tasks 
(
    id          serial       primary key,
    name        varchar(160) not null,
    guidelines  text         null,
    state       boolean      not null default true
);


insert into tasks ( name ) values ( 'Arrumar camas' );
insert into tasks ( name ) values ( 'Lavar roupas' );
insert into tasks ( name ) values ( 'Dedetizar contra insetos' );
insert into tasks ( name ) values ( 'Descongelar geladeira/freezer' );
insert into tasks ( name ) values ( 'Limpar caixa d''água' );
insert into tasks ( name ) values ( 'Lavar cortinas e tapetes' );
insert into tasks ( name ) values ( 'Limpar o vaso sanitário' );
insert into tasks ( name ) values ( 'Limpar eletrodomésticos' );
insert into tasks ( name ) values ( 'Limpar guarda-roupa' );
insert into tasks ( name ) values ( 'Limpar aberturas' );
insert into tasks ( name ) values ( 'Limpar chão' );
insert into tasks ( name ) values ( 'Varrer chão' );
insert into tasks ( name ) values ( 'Limpar vidros' );
insert into tasks ( name ) values ( 'Organizar despensa' );
insert into tasks ( name ) values ( 'Passar aspirador' );
insert into tasks ( name ) values ( 'Passar roupas' );
insert into tasks ( name ) values ( 'Recolher lixo' );
insert into tasks ( name ) values ( 'Revisar roupas' );
insert into tasks ( name ) values ( 'Tirar pó' );
insert into tasks ( name ) values ( 'Trocar toalhas' );
insert into tasks ( name ) values ( 'Limpar filtro ar-condicionado' );
insert into tasks ( name ) values ( 'Limpar lareira' );