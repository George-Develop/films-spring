-- Добавляем жанры
INSERT INTO genres (name) VALUES
                              ('Драма'),
                              ('Комедия'),
                              ('Фантастика'),
                              ('Приключения'),
                              ('Триллер');

-- Добавляем актёров
INSERT INTO actors (first_name, last_name, birth_date) VALUES
                                                           ('Константин', 'Хабенский', '1972-01-11'),
                                                           ('Сергей', 'Безруков', '1973-10-18'),
                                                           ('Чулпан', 'Хаматова', '1975-12-01'),
                                                           ('Данила', 'Козловский', '1985-05-03'),
                                                           ('Ирина', 'Рахманова', '1980-04-20'),
                                                           ('Юлия', 'Снигирь', '1983-08-16');

-- Добавляем фильмы
INSERT INTO films (title, description, release_year) VALUES
                                                         ('Левиафан', 'История маленького городка на севере России, борьба с коррумпированными властями', 2014),
                                                         ('Духless', 'Современная жизнь успешного бизнесмена в Москве', 2012),
                                                         ('Легенда №17', 'Биография хоккеиста Валерия Харламова', 2013),
                                                         ('Вий', 'Советская экранизация повести Гоголя о ведьме', 1967),
                                                         ('Сталинград', 'Военная драма о сражении за Сталинград во время ВОВ', 2013),
                                                         ('Т-34', 'Русский боевик о танкистах во Второй мировой войне', 2018);

-- Связываем фильмы с жанрами
-- Левиафан: Драма
INSERT INTO film_genres (film_id, genre_id) VALUES (1, 1);

-- Духless: Драма, Комедия
INSERT INTO film_genres (film_id, genre_id) VALUES (2, 1), (2, 2);

-- Легенда №17: Драма, Приключения
INSERT INTO film_genres (film_id, genre_id) VALUES (3, 1), (3, 4);

-- Вий: Фантастика, Приключения
INSERT INTO film_genres (film_id, genre_id) VALUES (4, 3), (4, 4);

-- Сталинград: Драма, Триллер, Приключения
INSERT INTO film_genres (film_id, genre_id) VALUES (5, 1), (5, 4), (5, 5);

-- Т-34: Приключения, Драма, Триллер
INSERT INTO film_genres (film_id, genre_id) VALUES (6, 4), (6, 1), (6, 5);

-- Связываем фильмы с актёрами и их ролями
-- Левиафан
INSERT INTO film_actors (film_id, actor_id, role_name) VALUES
                                                           (1, 1, 'Коля'),
                                                           (1, 3, 'Лилия');

-- Духless
INSERT INTO film_actors (film_id, actor_id, role_name) VALUES
                                                           (2, 2, 'Дмитрий'),
                                                           (2, 6, 'Аня');

-- Легенда №17
INSERT INTO film_actors (film_id, actor_id, role_name) VALUES
                                                           (3, 4, 'Валерий Харламов'),
                                                           (3, 5, 'Жена Валерия');

-- Вий
INSERT INTO film_actors (film_id, actor_id, role_name) VALUES
                                                           (4, 1, 'Гоголь'),
                                                           (4, 3, 'Прокопий');

-- Сталинград
INSERT INTO film_actors (film_id, actor_id, role_name) VALUES
                                                           (5, 2, 'Младший сержант'),
                                                           (5, 4, 'Танкист');

-- Т-34
INSERT INTO film_actors (film_id, actor_id, role_name) VALUES
                                                           (6, 4, 'Капитан'),
                                                           (6, 6, 'Сестра танкистов');
