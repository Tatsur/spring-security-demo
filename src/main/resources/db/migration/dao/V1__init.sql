create table users (
  id                    bigserial,
  username              varchar(30) unique,
  password              varchar(80) not null,
  score                 integer,
  primary key (id)
);

create table roles (
  id                    serial,
  name                  varchar(50) not null,
  primary key (id)
);

CREATE TABLE users_roles (
  user_id               bigint not null,
  role_id               int not null,
  primary key (user_id, role_id),
  foreign key (user_id) references users (id),
  foreign key (role_id) references roles (id)
);

insert into roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN');

insert into users (username, password, score)
values
('user1', '$2a$10$AL3BxKTjXlbu7.9NA5hRPutAw752Mo3pA8QnZV7FnGbxOxyHAuqcG', 0),
('user2', '$2a$10$AL3BxKTjXlbu7.9NA5hRPutAw752Mo3pA8QnZV7FnGbxOxyHAuqcG', 0);

insert into users_roles (user_id, role_id)
values
(1, 1),
(1, 2);