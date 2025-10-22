ALTER TABLE article
ADD COLUMN date_creation TIMESTAMP DEFAULT now();

ALTER TABLE article
ADD COLUMN date_edition TIMESTAMP;
