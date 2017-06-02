INSERT INTO countries (name) VALUES ('Ukraine');

INSERT INTO cities (name, country_id) SELECT
                                        'Kiev',
                                        countries.id
                                      FROM countries
                                      LIMIT 1;