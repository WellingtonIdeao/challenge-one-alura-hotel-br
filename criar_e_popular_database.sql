CREATE DATABASE hotel_alura;
USE hotel_alura;

CREATE TABLE nacionalidade(
id INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(50) NOT NULL
) ENGINE = InnoDB;

CREATE TABLE forma_pagamento (
id INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(20) NOT NULL
) ENGINE = InnoDB;

CREATE TABLE reserva (
id INT AUTO_INCREMENT,
data_entrada DATE NOT NULL,
data_saida DATE NOT NULL,
valor decimal(10,2) NOT NULL,
forma_pagamento_id INT,
PRIMARY KEY(id), 
FOREIGN KEY(forma_pagamento_id) REFERENCES forma_pagamento(id)
) ENGINE = InnoDB;

CREATE TABLE hospede (
id INT AUTO_INCREMENT,
nome VARCHAR(50) NOT NULL,
sobrenome VARCHAR(50) NOT NULL,
nacionalidade_id INT,
telefone VARCHAR(11),
data_nascimento DATE,
reserva_id INT,
PRIMARY KEY (id),
FOREIGN KEY(nacionalidade_id) REFERENCES nacionalidade(id),
FOREIGN KEY(reserva_id) REFERENCES reserva(id)
) ENGINE = InnoDB;

CREATE TABLE user (
id INT AUTO_INCREMENT,
username varchar(30) NOT NULL UNIQUE,
password char(64) NOT NULL,
PRIMARY KEY(id) 
) ENGINE = InnoDB;

INSERT INTO user(username, password) VALUES("admin", sha2("admin", 256));

INSERT INTO nacionalidade (nome) VALUES ("Brasileiro");
INSERT INTO nacionalidade (nome) VALUES ("Espanhol");
INSERT INTO nacionalidade (nome) VALUES ("Americano");

INSERT INTO forma_pagamento (nome) values ("Cartão de Credito");
INSERT INTO forma_pagamento (nome) values ("Cartão de Debito");
INSERT INTO forma_pagamento (nome) values ("Boleto");
