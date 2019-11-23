SELECT * FROM db_example.users;
SELECT * FROM db_example.roles;
SELECT * FROM db_example.user_roles;

DROP TABLE db_example.users;
DROP TABLE db_example.roles;
DROP TABLE db_example.user_roles;

-- Table: users
CREATE TABLE users (
  id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  age INT,
  role VARCHAR(255) NOT NULL,
  role_id bigint
)
  ENGINE = InnoDB;

-- Table: roles
CREATE TABLE roles (
  id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
  rolename VARCHAR(100) NOT NULL
)
  ENGINE = InnoDB;

-- Table for mapping user and roles: user_roles
CREATE TABLE user_roles (
  user_id bigint NOT NULL,
  role_id bigint NOT NULL,

  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (role_id) REFERENCES roles (id),

  UNIQUE (user_id, role_id)
)
  ENGINE = InnoDB;

-- Insert data

INSERT INTO roles VALUES (1, 'ROLE_USER');
INSERT INTO roles VALUES (2, 'ROLE_ADMIN');

-- UserName: admin; Password: 1
INSERT INTO users VALUES (1, 'admin', '$2a$10$FimkM/GY3KKDfepfx/2UeujXl4L3MnQekqnVdps9xcRRgg6hqUAeG', 31, 'ROLE_ADMIN', 2);
INSERT INTO user_roles VALUES(1, 2);