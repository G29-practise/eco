create table user_permission
(
    user_id       uuid references "user",
    permission_id varchar references permission,
    primary key (user_id, permission_id)
);

create table user_role
(
    user_id uuid references "user",
    role_id varchar references role,
    primary key (user_id, role_id)
);