CREATE TABLE IF NOT EXISTS addresses(
    id BIGSERIAL PRIMARY KEY,
    zip_code VARCHAR(10),
    street VARCHAR(100),
    number VARCHAR(10),
    complement VARCHAR(100),
    neighborhood VARCHAR(100),
    city VARCHAR(100),
    state VARCHAR(2),
    country VARCHAR(100),
    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION
);

CREATE TABLE IF NOT EXISTS user_preferences(
    id BIGSERIAL PRIMARY KEY,
    notification_email BOOLEAN NOT NULL DEFAULT TRUE,
    notification_sms BOOLEAN NOT NULL DEFAULT FALSE,
    notification_push BOOLEAN NOT NULL DEFAULT TRUE,
    privacy_profile BOOLEAN NOT NULL DEFAULT FALSE,
    privacy_contact BOOLEAN NOT NULL DEFAULT FALSE,
    language VARCHAR(10) DEFAULT 'pt-BR',
    timezone VARCHAR(50) DEFAULT 'America/Sao_Paulo',
    dark_mode BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    nick_name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    email VARCHAR(150) NOT NULL UNIQUE,
    phone VARCHAR(20),
    birth_date DATE,
    profile_photo_url TEXT,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    last_presence TIMESTAMP,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    address_id BIGINT REFERENCES addresses(id) ON DELETE SET NULL,
    preferences_id BIGINT REFERENCES user_preferences(id) ON DELETE SET NULL
);



CREATE TABLE IF NOT EXISTS user_group_roles(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    group_id BIGINT NOT NULL,
    role VARCHAR(20) NOT NULL,
    joined_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    UNIQUE(user_id, group_id)
);

CREATE INDEX idx_users_email ON users(email);

COMMENT ON TABLE users IS 'Tabela principal de usuários do sistema';
COMMENT ON TABLE addresses IS 'Endereco dos usuários';
COMMENT ON TABLE user_preferences IS 'Preferências personalizadas de cada usuário';
COMMENT ON TABLE user_group_roles IS 'Relacimento entre usuários e grupos com seus respectivos papéis';
--COMMENT ON TABLE authentication IS 'Métodos de authenticação dos usuários';