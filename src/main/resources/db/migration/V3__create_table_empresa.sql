CREATE TABLE empresa (

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

    cnpj varchar(14) NOT NULL,
    razao_social varchar(100) NOT NULL,
    nome_fantasia varchar(100),
    area_de_atuacao varchar(100),

    ativo tinyint not null,

    PRIMARY KEY(id)
)
