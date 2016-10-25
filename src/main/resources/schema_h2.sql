CREATE TABLE IF NOT EXISTS students (
  id         INT AUTO_INCREMENT,
  name       VARCHAR(255),
  sirname    VARCHAR(255),
  group_name VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS lessons (
  id     INT AUTO_INCREMENT,
  lesson VARCHAR(255),
  date   DATE
);