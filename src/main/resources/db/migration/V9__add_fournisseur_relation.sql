-- Ajoute la table fournisseur si elle n'existe pas
CREATE TABLE IF NOT EXISTS fournisseur (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(255),
    reference_article VARCHAR(255)
);

-- Ajoute la colonne de clé étrangère dans article
ALTER TABLE article
    ADD COLUMN fournisseur_principal_id INTEGER REFERENCES fournisseur(id);

-- (Optionnel) Si tu veux migrer les anciennes valeurs du champ fournisseur_principal (VARCHAR) vers la nouvelle table, il faudra un script de migration plus avancé.