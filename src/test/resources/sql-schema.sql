DROP database if exists ims_testDB;
CREATE SCHEMA IF NOT EXISTS ims_testDB;
USE ims_test;
CREATE TABLE IF NOT EXISTS ims_testDB.customers(Customer_id INT NOT NULL AUTO_INCREMENT unique, first_name VARCHAR(50) NULL DEFAULT NULL, surname VARCHAR(40) NULL DEFAULT NULL,PRIMARY KEY (Customer_id));
CREATE TABLE IF NOT EXISTS ims_testDB.item(Item_id INT NOT NULL AUTO_INCREMENT unique,Item_name VARCHAR(50) NULL DEFAULT NULL,Price DOUBLE NULL DEFAULT NULL,Stock INT NULL DEFAULT NULL,	PRIMARY KEY (Item_id));
CREATE TABLE IF NOT EXISTS ims_testDB.`order`(Order_id INT NOT NULL AUTO_INCREMENT unique,Customer_id INT NOT NULL,PRIMARY KEY (Order_id), FOREIGN KEY (`Customer_id`) REFERENCES customers(`Customer_id`));
CREATE TABLE IF NOT EXISTS ims_testDB.order_items(Order_ItemsID INT NOT NULL AUTO_INCREMENT unique,Order_id INT NOT NULL,Item_id INT NOT NULL,Quantity INT NOT NULL,Overall_Price Double NOT NULL,PRIMARY KEY (Order_ItemsID),FOREIGN KEY (Order_id) REFERENCES `order`(Order_id), FOREIGN KEY (Item_id) REFERENCES item(Item_id));
