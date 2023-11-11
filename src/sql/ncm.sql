
DROP TABLE ncm;

DROP table ncm CASCADE;

CREATE TABLE ncm (
    id SERIAL PRIMARY KEY,
    codigo VARCHAR(10) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    ativo BOOLEAN NOT NULL,
    deletado BOOLEAN NOT NULL DEFAULT false
);

SELECT * FROM ncm;
