
DROP TABLE item;

CREATE TABLE item (
    id SERIAL PRIMARY KEY,
    produto_id INT REFERENCES produto (id),
    complemento TEXT,
    quantidade NUMERIC(10, 4) NOT NULL,
    tipo INTEGER NOT NULL,
    transacao_id INTEGER NOT NULL
);

ALTER TABLE item ADD COLUMN deletado BOOLEAN NOT NULL DEFAULT false;


SELECT * FROM item;
 