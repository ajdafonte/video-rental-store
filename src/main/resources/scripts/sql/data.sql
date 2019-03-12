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
