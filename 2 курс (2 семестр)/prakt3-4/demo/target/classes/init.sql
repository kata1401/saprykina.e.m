-- Создание таблиц
CREATE TABLE IF NOT EXISTS users (
    id VARCHAR(36) PRIMARY KEY,
    login VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL,
    full_name VARCHAR(255),
    snils VARCHAR(14),
    birth_date DATE
);

CREATE TABLE IF NOT EXISTS votings (
    id VARCHAR(36) PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    end_date TIMESTAMP NOT NULL,
    is_active BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS candidates (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    biography TEXT,
    user_id VARCHAR(36)
);

CREATE TABLE IF NOT EXISTS votes (
    user_id VARCHAR(36) NOT NULL,
    voting_id VARCHAR(36) NOT NULL,
    candidate_id VARCHAR(36) NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, voting_id)
);