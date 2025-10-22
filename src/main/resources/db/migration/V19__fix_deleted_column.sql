-- V16__fix_deleted_column.sql
-- Corriger les valeurs de la colonne deleted dans la table fournisseurs_centrales
-- et forcer un default à false

-- Convertir les anciennes valeurs en boolean
UPDATE fournisseurs_centrales
SET deleted = false
WHERE deleted::text = 'f';

UPDATE fournisseurs_centrales
SET deleted = true
WHERE deleted::text = 't';

-- Définir une valeur par défaut
ALTER TABLE fournisseurs_centrales
ALTER COLUMN deleted SET DEFAULT false;

-- S'assurer que la colonne est bien de type boolean
ALTER TABLE fournisseurs_centrales
ALTER COLUMN deleted TYPE boolean
USING deleted::boolean;
