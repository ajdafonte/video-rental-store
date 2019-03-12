DELETE FROM film;
DELETE FROM filmtype;

-- film types
INSERT INTO filmtype VALUES (1, 'New Release');
INSERT INTO filmtype VALUES (2, 'Regular');
INSERT INTO filmtype VALUES (3, 'Old');

-- films
INSERT INTO film (name, typeid) VALUES ('Saving Private Ryan', 3);
INSERT INTO film (name, typeid) VALUES ('Green Book', 1);
INSERT INTO film (name, typeid) VALUES ('Spider Man', 2);

-- customers
INSERT INTO customer (username, email, bonuspoints) VALUES ('cr7', 'cr7@email.com', 4);
INSERT INTO customer (username, email, bonuspoints) VALUES ('lapulga', 'lapulga@email.com', 3);
