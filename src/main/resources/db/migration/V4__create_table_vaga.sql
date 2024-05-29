CREATE TABLE vaga (

  id BIGINT NOT NULL AUTO_INCREMENT,
  titulo varchar(100) NOT NULL,
  cargo varchar(100) NOT NULL,
  formato_de_trabalho ENUM('REMOTO', 'HIBRIDO', 'PRESENCIAL') NOT NULL,
  empresa_id BIGINT NOT NULL,
  cidade varchar(100),
  formacao_requerida VARCHAR(100) NOT NULL,
  experiencia_requerida INT(2) UNSIGNED ZEROFILL DEFAULT 0,
  habilidades_requeridas varchar(255),
  descricao TEXT,
  salario DECIMAL(10,2),
  data_publicacao DATE NOT NULL,
  ativo tinyint not null,

  PRIMARY KEY(id),
  CONSTRAINT fk_empresa_id FOREIGN KEY(empresa_id) REFERENCES empresa(id)
)
