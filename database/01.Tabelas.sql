--DROP TABLE datas;

CREATE TABLE datas (
	data date NOT NULL,
	dt_dia_nome character varying(50) NOT NULL,
	dt_mes date NOT NULL,
	dt_mes_nome character varying(50) NOT NULL,
	dt_semana date NOT NULL,
	CONSTRAINT pk_datas PRIMARY KEY (data)
);

--DROP TABLE usuarios;

CREATE TABLE usuarios (
	id serial NOT NULL,
	nome character varying(100) NOT NULL,
	cpf character varying(11) NOT NULL,
    usuarioSistema character varying(100) NOT NULL,
    senha character varying(100) NOT NULL,
    administrador boolean NOT NULL,
	CONSTRAINT pk_usuarios PRIMARY KEY (id)
);

--DROP TABLE tipos_marcacoes_horarios;

CREATE TABLE tipos_marcacoes_horarios (
	id serial NOT NULL,
	tipo_registro character varying(100) NOT NULL,
	CONSTRAINT pk_tipos_marcacoes_horarios PRIMARY KEY (id)
);

--DROP TABLE marcacoes_horarios;

CREATE TABLE marcacoes_horarios (
	dt_registro timestamp without time zone NOT NULL,
	id_tipo_registro int NOT NULL,
	dt_alteracao timestamp without time zone NULL,
	id_usuario int NOT NULL,
	CONSTRAINT pk_marcacoes_horarios PRIMARY KEY (dt_registro),
	CONSTRAINT fk_tipos_marcacoes_horarios FOREIGN KEY (id_tipo_registro) REFERENCES tipos_marcacoes_horarios (id),
	CONSTRAINT fk_usuarios FOREIGN KEY (id_usuario) REFERENCES usuarios (id)
);

