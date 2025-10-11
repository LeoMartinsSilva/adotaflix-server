create table raca(
	id_raca bigserial primary key,
	nm_raca varchar(255) not null,
	id_especie bigint not null,
	constraint fk_raca_especie foreign key (id_especie) references especie(id_especie)
);