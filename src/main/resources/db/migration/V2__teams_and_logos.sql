CREATE TABLE team (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      league_id BIGINT NOT NULL,
                      name VARCHAR(100) NOT NULL,
                      short_name VARCHAR(20),
                      color_primary VARCHAR(20),
                      color_secondary VARCHAR(20),
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      CONSTRAINT fk_team_league
                          FOREIGN KEY (league_id) REFERENCES leagues(id)
);

CREATE TABLE team_logo (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           team_id BIGINT NOT NULL,
                           file_name VARCHAR(150),
                           file_url VARCHAR(255),
                           is_default BOOLEAN DEFAULT TRUE,
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           CONSTRAINT fk_logo_team
                               FOREIGN KEY (team_id) REFERENCES team(id)
);

CREATE INDEX idx_team_leagues ON team(league_id);
