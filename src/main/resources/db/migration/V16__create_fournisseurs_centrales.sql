
CREATE TABLE IF NOT EXISTS fournisseurs_centrales (
    id BIGSERIAL PRIMARY KEY,
    id_fournisseur_centrale BIGINT NOT NULL,
    id_fournisseur BIGINT NOT NULL,
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_fournisseur_centrale_centrale FOREIGN KEY (id_fournisseur_centrale)
        REFERENCES fournisseurs(id)
        ON UPDATE CASCADE ON DELETE CASCADE,

    CONSTRAINT fk_fournisseur_centrale_fournisseur FOREIGN KEY (id_fournisseur)
        REFERENCES fournisseurs(id)
        ON UPDATE CASCADE ON DELETE CASCADE,

    CONSTRAINT uniq_centrale_fournisseur UNIQUE (id_fournisseur_centrale, id_fournisseur)
);

CREATE INDEX IF NOT EXISTS idx_id_fournisseur_centrale ON fournisseurs_centrales(id_fournisseur_centrale);
CREATE INDEX IF NOT EXISTS idx_id_fournisseur ON fournisseurs_centrales(id_fournisseur);
