CREATE TABLE IF NOT EXISTS filmtype (
  id              BIGINT PRIMARY KEY NOT NULL,
  name            VARCHAR2(100)
);

CREATE TABLE IF NOT EXISTS film (
  id              BIGINT PRIMARY KEY AUTO_INCREMENT,
  name            VARCHAR2(100) NOT NULL UNIQUE,
  typeid         BIGINT

--   CONSTRAINT UK_FILM UNIQUE (id, name)
);

ALTER TABLE film
  ADD FOREIGN KEY (typeid) references filmtype(id)