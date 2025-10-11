create table solicitacao_adocao(
	id_solicitacao_adocao bigserial primary key,
	id_usuario bigint not null,
	id_animal bigint not null,
	dt_solicitacao timestamp not null,
	fl_resposta varchar(2),
	dt_resposta timestamp,
	ds_resposta text,
	constraint fk_solicitacao_adocao_usuario foreign key (id_usuario) references usuario(id_usuario),
	constraint fk_solicitacao_adocao_animal foreign key (id_animal) references animal(id_animal)
);