-- 🔹 Corriger les fournisseurs CENTRALE
UPDATE fournisseurs
SET type_id = 2
WHERE type = 'CENTRALE';

-- 🔹 Corriger les fournisseurs SIMPLE
UPDATE fournisseurs
SET type_id = 1
WHERE type = 'SIMPLE';

-- 🔹 Vérification facultative : liste des fournisseurs avec leur type_id
-- SELECT code_fournisseur, intitule_fournisseur, type, type_id FROM fournisseurs;
