CREATE TABLE genres (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE actors (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        first_name VARCHAR(100) NOT NULL,
                        last_name VARCHAR(100) NOT NULL,
                        birth_date DATE
);

CREATE TABLE films (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       title VARCHAR(255) NOT NULL,
                       description VARCHAR(1000),
                       release_year INT
);

CREATE TABLE film_genres (
                             film_id BIGINT NOT NULL,
                             genre_id BIGINT NOT NULL,
                             PRIMARY KEY (film_id, genre_id),
                             FOREIGN KEY (film_id) REFERENCES films(id) ON DELETE CASCADE,
                             FOREIGN KEY (genre_id) REFERENCES genres(id) ON DELETE CASCADE
);

CREATE TABLE film_actors (
                             film_id BIGINT NOT NULL,
                             actor_id BIGINT NOT NULL,
                             role_name VARCHAR(255),
                             PRIMARY KEY (film_id, actor_id),
                             FOREIGN KEY (film_id) REFERENCES films(id) ON DELETE CASCADE,
                             FOREIGN KEY (actor_id) REFERENCES actors(id) ON DELETE CASCADE
);
