-- Ajouter la colonne deleted si elle n'existe pas
ALTER TABLE fournisseurs
ADD COLUMN IF NOT EXISTS deleted BOOLEAN;

-- Mettre la valeur par défaut à false pour les nouvelles lignes
ALTER TABLE fournisseurs
ALTER COLUMN deleted SET DEFAULT FALSE;

-- Mettre les anciennes lignes à false si NULL
UPDATE fournisseurs
SET deleted = FALSE
WHERE deleted IS NULL;
