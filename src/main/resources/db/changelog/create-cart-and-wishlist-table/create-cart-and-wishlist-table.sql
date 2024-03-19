create table cart
(
    id      uuid primary key,
    user_id uuid unique,
    foreign key (user_id) references "user"
);

create table wishlist
(
    id      uuid primary key,
    user_id uuid unique,
    foreign key (user_id) references "user"
);

create table wishlist_product
(
    wishlist_id uuid references wishlist,
    product_id  uuid references product,
    primary key (wishlist_id, product_id)
);