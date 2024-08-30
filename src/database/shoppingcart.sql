CREATE DATABASE shoppingcart;

USE shoppingcart;

CREATE TABLE users (
	id int NOT NULL auto_increment,
    user_name varchar(255) not null,
    email varchar(255) not null,
    area_of_interest varchar(255) not null,
    PRIMARY KEY(id)
);

CREATE TABLE products (
	id int NOT NULL auto_increment,
    name varchar(255),
    total_products_inventory int not null,
    price double not null,
    description varchar(255),
    image VARCHAR(255),
    product_status boolean not null,
    PRIMARY KEY(id)
); 

SELECT * FROM wish_list_products;
SELECT * FROM order_history_products;
SELECT * FROM wish_list;
SELECT * FROM products;
SELECT * FROM users;

insert into wish_list(product_id, user_id)
values(1,7);

insert into products(description, image, name, price, product_status, total_products_inventory)
values('N/A', 'p.jpg', 'Pen', 10.5, true, 100);

insert into products(description, image, name, price, product_status, total_products_inventory)
values('N/A', 'p.jpg', 'Pencil', 10.5, true, 100);

insert into users(area_of_interest, email, user_name)
values('Soccer', 'peperala7@hotmail.com', 'PepeRL14');

insert into users(area_of_interest, email, user_name)
values('Tenis', 'peperala34@hotmail.com', 'PepeRL34');