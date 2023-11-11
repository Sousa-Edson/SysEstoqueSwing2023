drop table cliente;

CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    tipo_cliente INTEGER NOT NULL,
    cnpj VARCHAR(18) NOT NULL,
    razao_social VARCHAR(255) NOT NULL,
    nome_fantasia VARCHAR(255),
    inscricao_estadual VARCHAR(20),
    inscricao_municipal VARCHAR(20),
    endereco VARCHAR(255) NOT NULL,
    contato VARCHAR(255) ,
    responsavel_legal VARCHAR(255),
    tipo_empresa INTEGER NOT NULL,
    deletado BOOLEAN NOT NULL DEFAULT false 
);

ALTER TABLE cliente
ADD COLUMN ativo BOOLEAN DEFAULT TRUE;


SELECT * FROM cliente; 
 
 