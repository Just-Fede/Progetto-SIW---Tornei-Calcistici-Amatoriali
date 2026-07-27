-- ============================================================
-- import.sql - Popolamento del database Torneo Calcistico
-- IMPORTANTE: una istruzione SQL per riga (Hibernate usa un
-- parser single-line per eseguire import.sql: niente VALUES
-- multi-riga, altrimenti va in errore di sintassi)
-- ============================================================

-- ---------------------------
-- SQUADRA
-- ---------------------------
INSERT INTO squadra (id, nome, anno_fondazione, citta) VALUES (1, 'AS Roma Trastevere', 1985, 'Roma');
INSERT INTO squadra (id, nome, anno_fondazione, citta) VALUES (2, 'Milano United', 1972, 'Milano');
INSERT INTO squadra (id, nome, anno_fondazione, citta) VALUES (3, 'Napoli Vulcano FC', 1990, 'Napoli');
INSERT INTO squadra (id, nome, anno_fondazione, citta) VALUES (4, 'Torino Mole FC', 1968, 'Torino');

-- ---------------------------
-- TORNEO
-- ---------------------------
INSERT INTO torneo (id, nome, anno, descrizione) VALUES (1, 'Coppa Universitaria 2026', 2026, 'Torneo primaverile tra le migliori squadre studentesche');
INSERT INTO torneo (id, nome, anno, descrizione) VALUES (2, 'Torneo Estivo 2026', 2026, 'Competizione estiva a eliminazione diretta');

-- ---------------------------
-- ARBITRO
-- ---------------------------
INSERT INTO arbitro (id, nome, cognome) VALUES (1, 'Marco', 'Bianchi');
INSERT INTO arbitro (id, nome, cognome) VALUES (2, 'Luca', 'Verdi');
INSERT INTO arbitro (id, nome, cognome) VALUES (3, 'Alessandro', 'Ferrari');

-- ---------------------------
-- USERS (Utente)
-- ---------------------------
INSERT INTO users (id, username) VALUES (1, 'fede89');
INSERT INTO users (id, username) VALUES (2, 'giulia_r');
INSERT INTO users (id, username) VALUES (3, 'admin');
INSERT INTO users (id, username) VALUES (4, 'marco_t');

-- ---------------------------
-- CREDENZIALI
-- Password in chiaro: "password" (hash BCrypt sotto)
-- ---------------------------
INSERT INTO credenziali (id, username, password, role, utente_id) VALUES (1, 'fede89', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ROLE_DEFAULT', 1);
INSERT INTO credenziali (id, username, password, role, utente_id) VALUES (2, 'giulia_r', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ROLE_DEFAULT', 2);
INSERT INTO credenziali (id, username, password, role, utente_id) VALUES (3, 'admin', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ROLE_ADMIN', 3);
INSERT INTO credenziali (id, username, password, role, utente_id) VALUES (4, 'marco_t', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ROLE_DEFAULT', 4);

-- ---------------------------
-- GIOCATORE
-- (squadraId non e' una vera FK JPA, ma la valorizziamo comunque
-- in modo coerente con gli id delle squadre inserite sopra)
-- ---------------------------
INSERT INTO giocatore (id, nome, cognome, data_nascita, ruolo, altezza, squadra_id) VALUES (1, 'Davide', 'Rossi', '1999-03-14', 'Portiere', 1.88, 1);
INSERT INTO giocatore (id, nome, cognome, data_nascita, ruolo, altezza, squadra_id) VALUES (2, 'Simone', 'Colombo', '2000-07-22', 'Difensore', 1.82, 1);
INSERT INTO giocatore (id, nome, cognome, data_nascita, ruolo, altezza, squadra_id) VALUES (3, 'Andrea', 'Greco', '1998-11-02', 'Centrocampista', 1.76, 1);
INSERT INTO giocatore (id, nome, cognome, data_nascita, ruolo, altezza, squadra_id) VALUES (4, 'Matteo', 'Ricci', '2001-01-19', 'Attaccante', 1.79, 2);
INSERT INTO giocatore (id, nome, cognome, data_nascita, ruolo, altezza, squadra_id) VALUES (5, 'Federico', 'Marino', '1999-09-30', 'Difensore', 1.84, 2);
INSERT INTO giocatore (id, nome, cognome, data_nascita, ruolo, altezza, squadra_id) VALUES (6, 'Lorenzo', 'Costa', '2000-05-08', 'Attaccante', 1.81, 3);
INSERT INTO giocatore (id, nome, cognome, data_nascita, ruolo, altezza, squadra_id) VALUES (7, 'Gabriele', 'Fontana', '1998-12-25', 'Centrocampista', 1.77, 3);
INSERT INTO giocatore (id, nome, cognome, data_nascita, ruolo, altezza, squadra_id) VALUES (8, 'Riccardo', 'Serra', '1999-06-17', 'Portiere', 1.90, 4);
INSERT INTO giocatore (id, nome, cognome, data_nascita, ruolo, altezza, squadra_id) VALUES (9, 'Nicolò', 'Barbieri', '2000-02-11', 'Difensore', 1.83, 4);

-- ---------------------------
-- PARTECIPAZIONE (squadre iscritte ai tornei)
-- ---------------------------
INSERT INTO partecipazione (id, torneo_id, squadra_id) VALUES (1, 1, 1);
INSERT INTO partecipazione (id, torneo_id, squadra_id) VALUES (2, 1, 2);
INSERT INTO partecipazione (id, torneo_id, squadra_id) VALUES (3, 1, 3);
INSERT INTO partecipazione (id, torneo_id, squadra_id) VALUES (4, 1, 4);
INSERT INTO partecipazione (id, torneo_id, squadra_id) VALUES (5, 2, 1);
INSERT INTO partecipazione (id, torneo_id, squadra_id) VALUES (6, 2, 3);

-- ---------------------------
-- PARTITA
-- REGOLA: se la partita ha un risultato reale (gol segnati durante
-- il gioco) lo stato deve essere 'FINITA'. Se e' PROGRAMMATA e non
-- ancora giocata, i gol restano a 0-0 come semplice placeholder
-- (goals_home/goals_away sono int primitivi -> NOT NULL, quindi non
-- possono essere NULL, ma il valore 0-0 li' non rappresenta un
-- risultato: la partita non e' ancora stata giocata).
-- ---------------------------
INSERT INTO partita (id, data_ora, luogo, goals_home, goals_away, stato, torneo_id, squadra_home_id, squadra_away_id, arbitro_id) VALUES (1, '2026-04-05 15:00:00', 'Stadio Trastevere', 2, 1, 'FINITA', 1, 1, 2, 1);
INSERT INTO partita (id, data_ora, luogo, goals_home, goals_away, stato, torneo_id, squadra_home_id, squadra_away_id, arbitro_id) VALUES (2, '2026-04-05 17:30:00', 'Stadio San Paolo', 0, 0, 'FINITA', 1, 3, 4, 2);
INSERT INTO partita (id, data_ora, luogo, goals_home, goals_away, stato, torneo_id, squadra_home_id, squadra_away_id, arbitro_id) VALUES (3, '2026-04-12 15:00:00', 'Stadio Torino Mole', 1, 3, 'FINITA', 1, 4, 1, 3);
INSERT INTO partita (id, data_ora, luogo, goals_home, goals_away, stato, torneo_id, squadra_home_id, squadra_away_id, arbitro_id) VALUES (4, '2026-04-19 18:00:00', 'Stadio Milano United', 0, 0, 'PROGRAMMATA', 1, 2, 3, 1);
INSERT INTO partita (id, data_ora, luogo, goals_home, goals_away, stato, torneo_id, squadra_home_id, squadra_away_id, arbitro_id) VALUES (5, '2026-04-26 15:00:00', 'Stadio Trastevere', 3, 2, 'FINITA', 1, 1, 3, 2);
INSERT INTO partita (id, data_ora, luogo, goals_home, goals_away, stato, torneo_id, squadra_home_id, squadra_away_id, arbitro_id) VALUES (6, '2026-05-03 17:00:00', 'Stadio Torino Mole', 0, 0, 'PROGRAMMATA', 1, 4, 2, 3);
INSERT INTO partita (id, data_ora, luogo, goals_home, goals_away, stato, torneo_id, squadra_home_id, squadra_away_id, arbitro_id) VALUES (7, '2026-06-14 16:00:00', 'Stadio San Paolo', 2, 2, 'FINITA', 2, 3, 1, 1);
INSERT INTO partita (id, data_ora, luogo, goals_home, goals_away, stato, torneo_id, squadra_home_id, squadra_away_id, arbitro_id) VALUES (8, '2026-06-21 18:30:00', 'Stadio Trastevere', 0, 0, 'PROGRAMMATA', 2, 1, 3, 2);

-- ---------------------------
-- COMMENTO
-- ---------------------------
INSERT INTO commento (id, testo, utente_id, partita_id) VALUES (1, 'Che partita combattuta, complimenti a entrambe le squadre!', 1, 1);
INSERT INTO commento (id, testo, utente_id, partita_id) VALUES (2, 'Difesa impeccabile del Napoli Vulcano oggi.', 2, 2);
INSERT INTO commento (id, testo, utente_id, partita_id) VALUES (3, 'Non mi aspettavo questa rimonta della Roma Trastevere!', 4, 3);

-- ============================================================
-- Allineamento delle sequence (utile solo se in futuro passi a
-- ddl-auto=update; con create-drop non e' strettamente necessario
-- ma non causa errori)
-- ============================================================
-- Tabelle con GenerationType.IDENTITY: la sequence e' "owned" dalla colonna,
-- pg_get_serial_sequence la trova automaticamente.
SELECT setval(pg_get_serial_sequence('giocatore', 'id'), (SELECT MAX(id) FROM giocatore));
SELECT setval(pg_get_serial_sequence('partecipazione', 'id'), (SELECT MAX(id) FROM partecipazione));
SELECT setval(pg_get_serial_sequence('partita', 'id'), (SELECT MAX(id) FROM partita));
SELECT setval(pg_get_serial_sequence('commento', 'id'), (SELECT MAX(id) FROM commento));
SELECT setval(pg_get_serial_sequence('torneo', 'id'), (SELECT MAX(id) FROM torneo));
SELECT setval(pg_get_serial_sequence('squadra', 'id'), (SELECT MAX(id) FROM squadra));

-- Tabelle con GenerationType.AUTO: Hibernate usa una sequence propria
-- (non "owned" dalla colonna), quindi va referenziata per nome esplicito.
SELECT setval('arbitro_seq', (SELECT MAX(id) FROM arbitro));
SELECT setval('credenziali_seq', (SELECT MAX(id) FROM credenziali));
SELECT setval('users_seq', (SELECT MAX(id) FROM users));
