create table cart
(
    id uuid primary key ,
    user_id uuid unique ,
    foreign key(user_id) references "user"
);