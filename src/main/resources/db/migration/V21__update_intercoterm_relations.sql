ALTER TABLE intercoterm
DROP COLUMN IF EXISTS mode_transport;
ALTER TABLE intercoterm
DROP COLUMN IF EXISTS responsable_vendeur;

ALTER TABLE intercoterm
ADD COLUMN IF NOT EXISTS mode_transport_id BIGINT NOT NULL;
ALTER TABLE intercoterm
ADD COLUMN IF NOT EXISTS responsable_vendeur_id BIGINT NOT NULL;

ALTER TABLE intercoterm
ADD CONSTRAINT fk_intercoterm_mode_transports
FOREIGN KEY (mode_transport_id)
REFERENCES mode_transports(id)
ON DELETE SET NULL
ON UPDATE CASCADE;

ALTER TABLE intercoterm
ADD CONSTRAINT fk_intercoterm_responsables
FOREIGN KEY (responsable_vendeur_id)
REFERENCES responsables(id)
ON DELETE SET NULL
ON UPDATE CASCADE;
