ALTER TABLE article
ADD COLUMN status INTEGER DEFAULT 1;

UPDATE article SET status = 1 WHERE status IS NULL;
