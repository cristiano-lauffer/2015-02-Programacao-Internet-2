--DROP FUNCTION CRIAR_PERIODO_DATAS();

--SELECT CRIAR_PERIODO_DATAS()

CREATE OR REPLACE FUNCTION CRIAR_PERIODO_DATAS() RETURNS VOID AS $$
DECLARE
	dt_inicio DATE;
	dt_fim DATE;
	dt_dia_nome character varying(50);
	dt_mes date;
	dt_mes_nome character varying(50);
	dt_semana date;
	ano int;
BEGIN
	dt_inicio := CAST('2008-01-01' AS DATE);
	dt_fim := CAST('2050-12-31' AS DATE);

	TRUNCATE TABLE datas;

	WHILE dt_inicio < dt_fim LOOP

		ano := EXTRACT(YEAR FROM dt_inicio);
		
		dt_dia_nome := 
			CASE EXTRACT(DOW FROM dt_inicio)
				WHEN 0 THEN 'Domingo'
				WHEN 1 THEN 'Segunda'
				WHEN 2 THEN 'Terça'
				WHEN 3 THEN 'Quarta'
				WHEN 4 THEN 'Quinta'
				WHEN 5 THEN 'Sexta'
				WHEN 6 THEN 'Sábado'
			END;
		dt_mes := date_trunc('MONTH', dt_inicio);
		dt_mes_nome := 
			CASE EXTRACT(MONTH FROM dt_inicio)
				WHEN 1 THEN ano || '.Janeiro'
				WHEN 2 THEN ano || '.Fevereiro'
				WHEN 3 THEN ano || '.Março'
				WHEN 4 THEN ano || '.Abril'
				WHEN 5 THEN ano || '.Maio'
				WHEN 6 THEN ano || '.Junho'
				WHEN 7 THEN ano || '.Julho'
				WHEN 8 THEN ano || '.Agosto'
				WHEN 9 THEN ano || '.Setembro'
				WHEN 10 THEN ano || '.Outubro'
				WHEN 11 THEN ano || '.Novembro'
				WHEN 12 THEN ano || '.Dezembro'
			END;
		dt_semana := date_trunc('week', dt_inicio);

		INSERT INTO datas (data, dt_dia_nome, dt_mes, dt_mes_nome, dt_semana)
			VALUES (dt_inicio, dt_dia_nome, dt_mes, dt_mes_nome, dt_semana);

		dt_inicio := dt_inicio + interval '1 day';
	END LOOP;

	RETURN;
END;
$$
LANGUAGE PLPGSQL;

--DROP FUNCTION INSERIR_PERIODO_MARCACAO(dt_inicio DATE, dt_fim DATE, id_usuario INT, id_tipo_marcacao INT);

--SELECT INSERIR_PERIODO_MARCACAO(CAST('2015-08-01' AS DATE), CAST('2015-10-06' AS DATE), 1, 4);

CREATE OR REPLACE FUNCTION INSERIR_PERIODO_MARCACAO(
	dt_inicio DATE,
	dt_fim DATE,
	id_usuario INT,
	id_tipo_marcacao INT
) RETURNS VOID AS $$
DECLARE
	dt_dia_semana INT;
BEGIN
	WHILE dt_inicio <= dt_fim LOOP

		dt_dia_semana := EXTRACT(DOW FROM dt_inicio);

		--Se não for sábado nem domingo
		if dt_dia_semana <> 0 AND dt_dia_semana <> 6 THEN

			--Marcação de entrada
			INSERT INTO marcacoes_horarios (dt_marcacao, id_tipo_marcacao, id_usuario)
				VALUES (CAST(dt_inicio AS TIMESTAMP) + interval '8 hours', id_tipo_marcacao, id_usuario);
		
			--Marcação de saída
			INSERT INTO marcacoes_horarios (dt_marcacao, id_tipo_marcacao, id_usuario)
				VALUES (CAST(dt_inicio AS TIMESTAMP) + interval '18 hours', id_tipo_marcacao, id_usuario);
		END IF;

		dt_inicio := dt_inicio + interval '1 day';
	END LOOP;

	RETURN;
END;
$$
LANGUAGE PLPGSQL;

