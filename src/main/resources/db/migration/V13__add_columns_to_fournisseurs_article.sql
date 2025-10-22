ALTER TABLE fournisseurs_article
    ADD COLUMN status INTEGER,
    ADD COLUMN deleted BOOLEAN,
    ADD COLUMN date_creation TIMESTAMP,
    ADD COLUMN date_edition TIMESTAMP;