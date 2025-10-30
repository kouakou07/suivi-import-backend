UPDATE fournisseurs
SET type_id = 2
WHERE type = 'CENTRALE' AND type_id IS NULL;

UPDATE fournisseurs
SET type_id = 1

-- ALTER TABLE fournisseurs DROP COLUMN type;
