
CREATE TABLE IF NOT EXISTS Torneo (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    anno INT NOT NULL,
    descrizione TEXT
);

CREATE TABLE IF NOT EXISTS Squadra (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    anno_fondazione INT NOT NULL,
    citta VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS Giocatore (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
    data_nascita DATE NOT NULL,
    ruolo VARCHAR(50) NOT NULL,
    altezza DECIMAL(4,2),
    squadra_id INT NOT NULL,
    
    FOREIGN KEY (squadra_id) REFERENCES Squadra(id)
);

CREATE TABLE IF NOT EXISTS Arbitro (
    codice_arbitrale VARCHAR(50) PRIMARY KEY
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
);

CREATE TABLE IF NOT EXISTS Utente (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    ruolo VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS Partita (
    id SERIAL PRIMARY KEY,
    data_ora TIMESTAMP NOT NULL,
    luogo VARCHAR(100) NOT NULL,
    goals_home INT DEFAULT 0,
    goals_away INT DEFAULT 0,
    stato VARCHAR(20) NOT NULL,
    
    torneo_id INT NOT NULL,
    squadra_home_id INT NOT NULL,
    squadra_away_id INT NOT NULL,
    arbitro_id INT NOT NULL,
    
    FOREIGN KEY (torneo_id) REFERENCES Torneo(id),
    FOREIGN KEY (squadra_home_id) REFERENCES Squadra(id),
    FOREIGN KEY (squadra_away_id) REFERENCES Squadra(id),
    FOREIGN KEY (arbitro_id) REFERENCES Arbitro(id),
    
    CHECK (squadra_home_id <> squadra_away_id)
);

CREATE TABLE IF NOT EXISTS Partecipazione (
    id SERIAL PRIMARY KEY,
    squadra_id INT NOT NULL,
    torneo_id INT NOT NULL,

    FOREIGN KEY (squadra_id) REFERENCES Squadra(id),
    FOREIGN KEY (torneo_id) REFERENCES Torneo(id)
);
