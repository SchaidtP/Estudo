CREATE TABLE IF NOT EXISTS user_permission (
  id_user bigint REFERENCES users(id),
  id_permission bigint REFERENCES permission(id),
  PRIMARY KEY (id_user, id_permission),
  FOREIGN KEY (id_user) REFERENCES users(id),
  FOREIGN KEY (id_permission) REFERENCES permission(id)
);