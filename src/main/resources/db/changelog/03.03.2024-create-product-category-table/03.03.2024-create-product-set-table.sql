create table product_set
(
    id uuid primary key ,
    quantity int not null ,
    product_id uuid references product,
    cart_id uuid references cart
);