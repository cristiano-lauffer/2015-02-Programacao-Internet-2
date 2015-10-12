
--==============================================================
--Inserir sem os id's especificados para a sequence dos mesmos
--ser incrementada
--==============================================================

-- TRUNCATE TABLE usuarios;
INSERT INTO usuarios (nome, cpf, usuarioSistema, senha, administrador) VALUES
	('Cristiano', '01040337031', 'clauffer', '123', 'TRUE'),
	('Adminitrador', '12345678901', 'admin', '123', 'TRUE'),
	('Leonardo', '45678901234', 'ldutra', '123', 'FALSE'),
	('Gabriel', '78912345678', 'glauffer', '123', 'FALSE');

--TRUNCATE TABLE marcacoes_horarios,tipos_marcacoes_horarios;
INSERT INTO tipos_marcacoes_horarios (tipo_marcacao) VALUES
	('Marcação Ponto'),
	('Marcação Web'),
	('Edição'),
	('Férias');

--TRUNCATE TABLE cargos;
INSERT INTO cargos (nome_cargo) VALUES
	('Programador'),
	('Analista BI'),
	('Outros');