CREATE TABLE acheteurs (
    id BIGSERIAL PRIMARY KEY,
    status INTEGER DEFAULT 1,
    deleted BOOLEAN DEFAULT FALSE,
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_edition TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    civilite VARCHAR(10),
    prenom VARCHAR(100),
    nom VARCHAR(100),
    raison_sociale VARCHAR(255),
    adresse_1 VARCHAR(255),
    adresse_2 VARCHAR(255),
    ville VARCHAR(100),
    code_postal VARCHAR(20),
    pays_code_iso CHAR(3),
    telephone VARCHAR(50),
    email VARCHAR(100),
    numero_tva VARCHAR(50)
);