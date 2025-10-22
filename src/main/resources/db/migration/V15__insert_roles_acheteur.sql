-- Insérer le rôle seulement si la table roles existe
DO $$
BEGIN
    IF EXISTS (SELECT 1 FROM information_schema.tables
               WHERE table_name = 'roles') THEN
        INSERT INTO roles (libelle, status, deleted, date_creation, date_edition)
        SELECT 'Acheteur', 1, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
        WHERE NOT EXISTS (SELECT 1 FROM roles WHERE libelle = 'Acheteur');
    END IF;
END
$$;
