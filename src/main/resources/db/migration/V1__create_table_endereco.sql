create table endereco(
	id_endereco bigserial primary key,
	ds_cep varchar(20) not null,
	ds_logradouro varchar(255) not null,
	ds_numero integer,
	fl_sem_numero boolean,
	ds_estado varchar(2),
	ds_cidade varchar(255)
);