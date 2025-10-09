create table instituicao(
	id_instituicao bigint primary key,
	nm_instituicao varchar(255) not null,
	ds_documento varchar(18) not null,
	id_endereco bigint,
	ds_telefone varchar(20),
	ds_telefone_whatsapp varchar(20),
	constraint fk_instituicao_endereco foreign key (id_endereco) references endereco(id_endereco)
);