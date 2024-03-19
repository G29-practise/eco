create table rating
(
    id         uuid primary key,
    stars      int not null,
    user_id    uuid references "user",
    product_id uuid references product(id)
);