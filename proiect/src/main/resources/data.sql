INSERT INTO utilizator (email, password, username, rol)
SELECT 'admin@exemplu.com', '$2a$10$FSGUQD9ey495OzNtOyG.Yu4sPqjT0wsB4QVuqPkfItHuopgvDIhlC', 'admin', 'ADMIN'
WHERE NOT EXISTS (SELECT 1 FROM utilizator WHERE username = 'admin');

INSERT INTO utilizator (email, password, username, rol)
SELECT 'user1@exemplu.com', '$2a$10$O2GGFZEGnpLd8ictwwWtp.fQ2BAj7icDVFNtoaq9TW2SV8ZriFfrq', 'user1', 'USER'
WHERE NOT EXISTS (SELECT 1 FROM utilizator WHERE username = 'user1');


INSERT INTO film (id, titlu, gen, descriere)
SELECT 1, 'Inception', 'SF', 'Un film despre vise în straturi.'
WHERE NOT EXISTS (SELECT 1 FROM film WHERE id = 1);

INSERT INTO film (id, titlu, gen, descriere)
SELECT 2, 'Avatar', 'SF', 'Un film despre Pandora.'
WHERE NOT EXISTS (SELECT 1 FROM film WHERE id = 2);

INSERT INTO film (id, titlu, gen, descriere)
SELECT 3, 'The Dark Knight', 'ACTIUNE', 'Un film despre Batman.'
WHERE NOT EXISTS (SELECT 1 FROM film WHERE id = 3);


