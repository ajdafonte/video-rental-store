DELETE FROM film;
DELETE FROM filmtype;
DELETE FROM price;

DELETE FROM customer;

DELETE FROM rentalitem;
DELETE FROM rental;

-- prices
INSERT INTO price VALUES (1, 'Premium', 40);
INSERT INTO price VALUES (2, 'Basic', 30);

-- film types
INSERT INTO filmtype VALUES (1, 'New Release', 1);
INSERT INTO filmtype VALUES (2, 'Regular', 2);
INSERT INTO filmtype VALUES (3, 'Old', 2);

-- films
INSERT INTO film (id, name, typeid) VALUES (1, 'Matrix 11', 1);
INSERT INTO film (id, name, typeid) VALUES (2, 'Spider Man', 2);
INSERT INTO film (id, name, typeid) VALUES (3, 'Out of Africa', 3);

-- customers
INSERT INTO customer (id, username, email, bonuspoints) VALUES (1, 'buzz', 'buzz@email.com', 2);
INSERT INTO customer (id, username, email, bonuspoints) VALUES (2, 'woody', 'woody@email.com', 4);
INSERT INTO customer (id, username, email, bonuspoints) VALUES (3, 'nemo', 'nemo@email.com', 6);
INSERT INTO customer (id, username, email, bonuspoints) VALUES (4, 'dory', 'dory@email.com', 4);

-- rental
INSERT INTO rental (id, customerid, startdatetime) VALUES (1, 1, 1552694400000 /* 16-03-2019 */);
INSERT INTO rental (id, customerid, startdatetime) VALUES (2, 2, 1552780800000 /* 17-03-2019 */);

-- rentalitems
INSERT INTO rentalitem (id, rentalid, filmid, daysrented, price, surcharge, startdatetime, enddatetime)
VALUES (1, 1, 1, 2, 80, 0, 1552694400000 /* 16-03-2019 */, null);
INSERT INTO rentalitem (id, rentalid, filmid, daysrented, price, surcharge, startdatetime, enddatetime)
VALUES (2, 1, 2, 4, 120, 0, 1552694400000 /* 16-03-2019 */, null);

INSERT INTO rentalitem (id, rentalid, filmid, daysrented, price, surcharge, startdatetime, enddatetime)
VALUES (3, 2, 2, 2, 60, 0, 1552780800000 /* 17-03-2019 */, null);
INSERT INTO rentalitem (id, rentalid, filmid, daysrented, price, surcharge, startdatetime, enddatetime)
VALUES (4, 2, 3, 1, 30, 0, 1552780800000 /* 17-03-2019 */, null);

