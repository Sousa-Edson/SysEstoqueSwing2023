/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  edson
 * Created: 12 de nov de 2023
 */

DROP TABLE log;

CREATE TABLE log (
    id SERIAL PRIMARY KEY,
    id_usuario INT NOT NULL,
    classe_model VARCHAR(255) NOT NULL,
    tipo_alteracao VARCHAR(20) NOT NULL,
    marcado_como_deletado BOOLEAN NOT NULL,
    data_alteracao TIMESTAMP NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);

SELECT * FROM log; 