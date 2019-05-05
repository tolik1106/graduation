DELETE FROM user_roles;
DELETE FROM lunches;
DELETE FROM restaurants;
DELETE FROM votes;
DELETE FROM users;

ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 10000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@user.com', '000000'),
  ('Admin', 'admin@admin.com', '123456'),
  ('Petr', 'petr@user.com', '654321'),
  ('Sonya', 'sonya@user.com', '11111'),
  ('Lilo', 'lilo@user.com', '22222'),
  ('Bilbo', 'bilbo@user.com', '55555');

INSERT INTO user_roles(role, user_id) VALUES
  ('ROLE_USER', 10000),
  ('ROLE_ADMIN', 10001),
  ('ROLE_USER', 10001),
  ('ROLE_USER', 10002),
  ('ROLE_USER', 10003),
  ('ROLE_USER', 10004),
  ('ROLE_USER', 10005);

INSERT INTO restaurants (name) VALUES
  ('Il''molino'),
  ('Smorodina'),
  ('I''kafa'),
  ('Banka'),
  ('Spezo');

INSERT INTO lunches (description, price, restaurant_id) VALUES
  ('Bread', 0.25, 10006),
  ('Lemon tea', 1.2, 10006),
  ('Orange juice', 0.99, 10006),
  ('Milk', 0.85, 10007),
  ('Coffee', 2.11, 10007),
  ('Wine', 3.05, 10008),
  ('Cola', 0.65, 10008),
  ('Bacon', 1.75, 10008),
  ('Pizza', 2.15, 10009),
  ('Water', 0.11, 10009),
  ('Lobster', 6.35, 10010),
  ('Wine', 2.98, 10010),
  ('Bread', 0.27, 10010);

