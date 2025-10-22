-- =========================================
-- V17 : Gestion des commandes (version enrichie)
-- =========================================

-- ===================
-- Table : commandes
-- ===================
CREATE TABLE IF NOT EXISTS commandes (
    id BIGSERIAL PRIMARY KEY,
    status INT DEFAULT 1,
    deleted BOOLEAN DEFAULT FALSE,
    date_creation TIMESTAMP DEFAULT NOW(),
    date_edition TIMESTAMP DEFAULT NOW(),

    numero_dai VARCHAR(100) NOT NULL,
    numero_commande_fournisseur VARCHAR(100),
    numero_proforma VARCHAR(100),
    date_proforma DATE,
    incoterm VARCHAR(50),
    mode_envoi VARCHAR(50),
    client_destinataire VARCHAR(255),
    statut VARCHAR(50),

    date_livraison_souhaitee DATE,
    date_etd DATE,
    date_eta DATE,
    prix_revient NUMERIC(15,2),
    prix_vente NUMERIC(15,2),

    fournisseur_id BIGINT,
    user_id BIGINT,
    CONSTRAINT fk_commande_fournisseur FOREIGN KEY (fournisseur_id) REFERENCES fournisseurs(id)
);

-- ===================
-- Table : commande_articles
-- ===================
CREATE TABLE IF NOT EXISTS commande_articles (
    id BIGSERIAL PRIMARY KEY,
    status INT DEFAULT 1,
    deleted BOOLEAN DEFAULT FALSE,
    date_creation TIMESTAMP DEFAULT NOW(),
    date_edition TIMESTAMP DEFAULT NOW(),

    commande_id BIGINT NOT NULL,
    article_id BIGINT NOT NULL,
    quantite NUMERIC(15,2) NOT NULL,
    prix_unitaire NUMERIC(15,2),
    quantite_recue NUMERIC(15,2) DEFAULT 0,

    CONSTRAINT fk_ca_commande FOREIGN KEY (commande_id) REFERENCES commandes(id),
    CONSTRAINT fk_ca_article FOREIGN KEY (article_id) REFERENCES article(id)
);

-- ===================
-- Table : fdi
-- ===================
CREATE TABLE IF NOT EXISTS fdi (
    id BIGSERIAL PRIMARY KEY,
    status INT DEFAULT 1,
    deleted BOOLEAN DEFAULT FALSE,
    date_creation TIMESTAMP DEFAULT NOW(),
    date_edition TIMESTAMP DEFAULT NOW(),

    numero VARCHAR(100),
    date_fdi DATE,
    transitaire VARCHAR(255),

    commande_id BIGINT,
    CONSTRAINT fk_fdi_commande FOREIGN KEY (commande_id) REFERENCES commandes(id)
);

-- ===================
-- Table : documents_commandes
-- ===================
CREATE TABLE IF NOT EXISTS documents_commandes (
    id BIGSERIAL PRIMARY KEY,
    status INT DEFAULT 1,
    deleted BOOLEAN DEFAULT FALSE,
    date_creation TIMESTAMP DEFAULT NOW(),
    date_edition TIMESTAMP DEFAULT NOW(),

    type_document VARCHAR(100) NOT NULL, -- FACTURE, ASSURANCE, BSC, CERTIFICAT_ORIGINE, COLISAGE, etc.
    numero VARCHAR(100),
    date_reception DATE,
    lien_fichier VARCHAR(500),

    commande_id BIGINT,
    CONSTRAINT fk_document_commande FOREIGN KEY (commande_id) REFERENCES commandes(id)
);

-- ===================
-- Table : bon_livraison_import
-- ===================
CREATE TABLE IF NOT EXISTS bon_livraison_import (
    id BIGSERIAL PRIMARY KEY,
    status INT DEFAULT 1,
    deleted BOOLEAN DEFAULT FALSE,
    date_creation TIMESTAMP DEFAULT NOW(),
    date_edition TIMESTAMP DEFAULT NOW(),

    numero_be VARCHAR(100),
    date_entree DATE,
    livraison_partielle BOOLEAN DEFAULT FALSE,

    commande_id BIGINT,
    CONSTRAINT fk_bl_commande FOREIGN KEY (commande_id) REFERENCES commandes(id)
);

-- ===================
-- Table : paiements
-- ===================
CREATE TABLE IF NOT EXISTS paiements (
    id BIGSERIAL PRIMARY KEY,
    status INT DEFAULT 1,
    deleted BOOLEAN DEFAULT FALSE,
    date_creation TIMESTAMP DEFAULT NOW(),
    date_edition TIMESTAMP DEFAULT NOW(),

    date_echeance DATE,
    statut VARCHAR(50), -- ENUM géré en Java
    date_paiement DATE,
    montant NUMERIC(15,2),
    devise VARCHAR(10),
    banque VARCHAR(255),

    commande_id BIGINT,
    CONSTRAINT fk_paiement_commande FOREIGN KEY (commande_id) REFERENCES commandes(id)
);
