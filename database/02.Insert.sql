
-- TRUNCATE TABLE usuarios;
INSERT INTO usuarios (id, nome, cpf, usuarioSistema, senha, administrador) VALUES
	(1, 'Cristiano', '01040337031', 'clauffer', '123', 'TRUE'),
	(2, 'Adminitrador', '12345678901', 'admin', '123', 'TRUE'),
	(3, 'Leonardo', '45678901234', 'ldutra', '123', 'FALSE'),
	(4, 'Gabriel', '78912345678', 'glauffer', '123', 'FALSE');

--TRUNCATE TABLE marcacoes_horarios,tipos_marcacoes_horarios;
INSERT INTO tipos_marcacoes_horarios (id, tipo_marcacao) VALUES
	(1, 'Marcação Ponto'),
	(2, 'Marcação Web'),
	(3, 'Edição'),
	(4, 'Férias');