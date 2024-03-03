create table role
(
    name varchar primary key
);

create table permission
(
    name varchar primary key
);
create table role_permission
(
    role_id       varchar references role,
    permission_id varchar references permission,
    primary key (role_id, permission_id)
)