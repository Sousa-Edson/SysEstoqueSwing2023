
drop table unidade;


 DROP table unidade CASCADE;

 CREATE TABLE unidade (
    id serial PRIMARY KEY,
    sigla VARCHAR(10) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    ativo BOOLEAN NOT NULL
);

ALTER TABLE unidade
ADD deletado BOOLEAN DEFAULT false;

SELECT * FROM unidade;
