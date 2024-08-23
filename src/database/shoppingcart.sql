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
