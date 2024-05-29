CREATE TABLE curriculo (
  id BIGINT NOT NULL AUTO_INCREMENT,

  telefone VARCHAR(20),
  celular VARCHAR(20),
  email VARCHAR(100) NOT NULL UNIQUE,
  site VARCHAR(100),

  cep VARCHAR(8) NOT NULL,
  logradouro VARCHAR(255) NOT NULL,
  numero INT,
  complemento VARCHAR(255),
  bairro VARCHAR(255) NOT NULL,
  cidade VARCHAR(255) NOT NULL,
  uf char(2) NOT NULL,

  curso VARCHAR(100),
  nivel VARCHAR(100),
  instituicao VARCHAR(100),
  data_inicio_curso DATE,
  data_conclusao_curso DATE,

  nome VARCHAR(100) NOT NULL,
  sobrenome VARCHAR(100) NOT NULL,
  cpf VARCHAR(11) NOT NULL UNIQUE,
  data_nascimento DATE NOT NULL,
  genero VARCHAR(100),
  nacionalidade VARCHAR(20),

  ativo tinyint not null,

  PRIMARY KEY(id)
);
