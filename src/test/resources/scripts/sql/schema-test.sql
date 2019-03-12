CREATE TABLE IF NOT EXISTS filmtype (
  id              BIGINT PRIMARY KEY NOT NULL,
  name            VARCHAR2(100)
);

CREATE TABLE IF NOT EXISTS film (
  id              BIGINT PRIMARY KEY AUTO_INCREMENT,
  name            VARCHAR2(100) NOT NULL UNIQUE,
  typeid         BIGINT
);

ALTER TABLE film
  ADD FOREIGN KEY (typeid) references filmtype(id);

CREATE TABLE IF NOT EXISTS customer (
  id                  BIGINT PRIMARY KEY AUTO_INCREMENT,
  username            VARCHAR2(100) NOT NULL UNIQUE,
--   password            VARCHAR2(100) NOT NULL,
--   firstname           VARCHAR2(100),
--   lastname            VARCHAR2(100),
  email               VARCHAR2(100),
  bonuspoints         BIGINT
);