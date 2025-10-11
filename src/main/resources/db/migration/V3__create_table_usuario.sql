create table usuario(
	id_usuario bigserial primary key,
	nm_usuario varchar(255) not null,
	ds_email varchar(255) not null unique,
	ds_senha text not null,
	fl_role varchar(10) not null,
	id_instituicao bigint,
	id_endereco bigint not null,
	dt_cadastro timestamp,
	ds_telefone varchar(20),
	constraint fk_usuario_endereco foreign key (id_endereco) references endereco(id_endereco),
	constraint fk_usuario_instituicao foreign key (id_instituicao) references instituicao(id_instituicao)
);