INSERT INTO patients (birthday, email, full_name, login, password, phone, register_date, secret, sex, city_id, country_id, user_id)
VALUES ('1996-08-08', 'test@test.com', 'Іванов Іван Іванович', 'test1337', '-', '000', '0000-00-00', '-', 'MALE',
                      (SELECT cities.id
                       FROM cities
                       LIMIT 1), (SELECT countries.id
                                  FROM countries
                                  LIMIT 1), (SELECT users.id
                                             FROM users
                                             LIMIT 1));

INSERT INTO cards (patient_info) VALUES ('test');

INSERT INTO patient_card (card_id, patient_id) VALUES ((SELECT cards.id
                                                        FROM cards
                                                        WHERE patient_info = 'test'
                                                        LIMIT 1),
                                                       (SELECT users.id
                                                        FROM users
                                                        WHERE username = 'admin'
                                                        LIMIT 1));