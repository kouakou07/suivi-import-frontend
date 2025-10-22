ALTER TABLE article
ADD COLUMN fournisseur_id BIGINT;

ALTER TABLE article
ADD CONSTRAINT fk_article_fournisseur
FOREIGN KEY (fournisseur_id)
REFERENCES fournisseurs(id);
