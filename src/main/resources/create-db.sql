CREATE TABLE books (
  id         INTEGER not null primary key
        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),
  name VARCHAR(100),
  author  VARCHAR(100)
);