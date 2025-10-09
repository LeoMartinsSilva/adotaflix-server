create table animal(
	id_animal bigint primary key,
	nm_animal varchar(255) not null,
	ds_animal text not null,
	dt_nascimento timestamp,
	id_raca bigint not null,
	id_cor bigint not null,
	id_instituicao bigint not null,
	fl_porte varchar(1) not null,
	fl_status varchar(1) not null,
	fl_sexo varchar(1) not null,
	constraint fk_animal_raca foreign key (id_raca) references raca(id_raca),
	constraint fk_animal_cor foreign key (id_cor) references cor(id_cor),
	constraint fk_animal_instituicao foreign key (id_instituicao) references instituicao(id_instituicao)
);