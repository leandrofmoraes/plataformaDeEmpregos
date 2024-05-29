CREATE TABLE experiencia_profissional(
  id BIGINT NOT NULL AUTO_INCREMENT,
  empresa VARCHAR(100),
  cargo VARCHAR(50),
  data_inicio_cargo DATE,
  data_fim_cargo DATE,
  descricao TEXT,
  curriculo_id BIGINT,

  PRIMARY KEY(id)
);

