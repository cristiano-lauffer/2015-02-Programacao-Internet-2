
--DROP TABLE usuarios;

CREATE TABLE usuarios (
	id serial NOT NULL,
	nome character varying(100) NOT NULL,
	cpf character varying(11) NOT NULL,
    usuarioSistema character varying(100) NOT NULL,
    senha character varying(100) NOT NULL,
    administrador boolean NOT NULL,
	CONSTRAINT pk_produtos PRIMARY KEY (id)
);

