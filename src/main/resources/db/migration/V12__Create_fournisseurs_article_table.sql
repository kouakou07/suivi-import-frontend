CREATE TABLE fournisseurs_article (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    article_id BIGINT NOT NULL,
    fournisseur_id BIGINT NOT NULL
);