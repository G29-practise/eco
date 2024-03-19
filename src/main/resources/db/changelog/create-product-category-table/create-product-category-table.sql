create table product
(
    id           uuid primary key,
    title        varchar          not null,
    description  varchar          not null,
    brand        varchar          not null,
    quantity     int              not null,
    discount     int,
    weight       double precision not null,
    price        double precision not null,
    file_name    varchar          not null,
    content_type varchar          not null,
    img          varchar,
    created      timestamp        not null,
    updated      timestamp        not null
);

create table category
(
    name varchar primary key
);

create table product_category
(
    category_name varchar references category,
    product_id    uuid references product,
    primary key (category_name, product_id)
);