DELETE FROM film;
DELETE FROM filmtype;

-- film types
INSERT INTO filmtype VALUES (1, 'New Release');
INSERT INTO filmtype VALUES (2, 'Regular');
INSERT INTO filmtype VALUES (3, 'Old');

-- films
INSERT INTO film (name, typeid) VALUES ('Matrix 11', 1);
INSERT INTO film (name, typeid) VALUES ('Spider Man', 2);
INSERT INTO film (name, typeid) VALUES ('Out of Africa', 3);

-- customers
INSERT INTO customer (username, email, bonuspoints) VALUES ('buzz', 'buzz@email.com', 2);
INSERT INTO customer (username, email, bonuspoints) VALUES ('woody', 'woody@email.com', 4);
INSERT INTO customer (username, email, bonuspoints) VALUES ('nemo', 'nemo@email.com', 6);
INSERT INTO customer (username, email, bonuspoints) VALUES ('dory', 'dory@email.com', 4);