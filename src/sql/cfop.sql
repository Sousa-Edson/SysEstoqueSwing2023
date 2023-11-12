
drop table cfop;

CREATE TABLE cfop (
    id SERIAL PRIMARY KEY,
    codigo VARCHAR(10) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    ativo BOOLEAN NOT NULL,
    deletado BOOLEAN NOT NULL DEFAULT false
);

ALTER TABLE cfop
ALTER COLUMN deletado SET DEFAULT false;
 

SELECT * FROM cfop;