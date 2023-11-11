
drop TABLE transacao;

CREATE TABLE transacao (
    id SERIAL PRIMARY KEY,
    tipo INTEGER NOT NULL,
    cfop INTEGER NOT NULL,
    cliente INTEGER NOT NULL,
    nota VARCHAR(20),
    chave VARCHAR(50),
    data_transacao DATE ,
    hora_transacao TIME,
    informacoes_complementares TEXT,
    deletado BOOLEAN NOT NULL DEFAULT false
);

ALTER TABLE transacao
ADD COLUMN nome_motorista VARCHAR(100);

ALTER TABLE transacao
ADD COLUMN status_nota INTEGER NOT NULL DEFAULT 0;

ALTER TABLE transacao
ALTER COLUMN chave TYPE VARCHAR(54);

SELECT * FROM transacao ;

