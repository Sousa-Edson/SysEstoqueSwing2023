
drop table produto;

 DROP table produto CASCADE;

CREATE TABLE produto (
    id SERIAL PRIMARY KEY,
    descricao TEXT NOT NULL,
    unidade_id INT REFERENCES unidade(id),
    valor NUMERIC(10, 2) NOT NULL,
    ncm_id INT REFERENCES ncm(id),
    observacao TEXT,
    ativo BOOLEAN DEFAULT true,
    deletado BOOLEAN DEFAULT false
);

ALTER TABLE produto
ALTER COLUMN valor TYPE NUMERIC(10,4);

SELECT * FROM produto;