CREATE SCHEMA agenda;

CREATE TABLE contatos (
	id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    telefone VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    dataNascimento VARCHAR(45) NOT NULL
);
