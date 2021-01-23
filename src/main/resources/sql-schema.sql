drop schema ims;
create database if not exists ims;
create table if not exists ims.customers(Customer_id INT primary key auto_increment, first_name VARCHAR(40), surname VARCHAR(40));
CREATE TABLE IF NOT EXISTS ims.item (Item_id INT AUTO_INCREMENT unique, Item_name VARCHAR (50) UNIQUE, Price INT, Quantity INT,PRIMARY KEY (Item_id));
CREATE TABLE IF NOT EXISTS ims.`order` (Order_id INT AUTO_INCREMENT, FK_Customer_id INT,FK_Item_name VARCHAR (50) ,PRIMARY KEY (Order_id),FOREIGN KEY (FK_Customer_id) REFERENCES customers(Customer_id), FOREIGN KEY (FK_Item_name) REFERENCES item(Item_name));
CREATE TABLE IF NOT EXISTS ims.Order_Items(Order_ItemsID INT AUTO_INCREMENT ,FK_Order_id INT,FK_Item_id INT,PRIMARY KEY (Order_ItemsID), FOREIGN KEY (FK_Order_id) REFERENCES ims.order(Order_id), FOREIGN KEY (FK_Item_id) REFERENCES item(Item_id));