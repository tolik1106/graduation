DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS lunches;
DROP TABLE IF EXISTS votes;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS users;

DROP SEQUENCE IF EXISTS GLOBAL_SEQ;

CREATE SEQUENCE GLOBAL_SEQ START WITH 10000;

CREATE TABLE users (
  id BIGINT DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  registered TIMESTAMP DEFAULT now(),
  enabled BOOLEAN DEFAULT TRUE
);
CREATE UNIQUE INDEX users_unique_email_idx ON users(email);

CREATE TABLE user_roles
(
  user_id BIGINT NOT NULL,
  role    VARCHAR(255),
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE restaurants (
  id BIGINT DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
  name VARCHAR(64) NOT NULL,
  UNIQUE(name)
);

CREATE TABLE lunches (
  id BIGINT DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
  description VARCHAR(255) NOT NULL,
  price DECIMAL NOT NULL,
  restaurant_id BIGINT NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants(id) ON DELETE CASCADE
);

CREATE TABLE votes (
  id BIGINT DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
  voted_date DATE NOT NULL,
  voted_time TIME NOT NULL,
  restaurant_id BIGINT NOT NULL,
  user_id BIGINT NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants(id),
  FOREIGN KEY (user_id) REFERENCES users(id)
);
CREATE UNIQUE INDEX votes_unique_user_date_idx ON votes (user_id, voted_date);