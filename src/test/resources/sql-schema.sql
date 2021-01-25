CREATE SCHEMA IF NOT EXISTS `ims`;
CREATE TABLE IF NOT EXISTS ims.customers(
    Customer_id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50) NULL DEFAULT NULL,
    surname VARCHAR(40) NULL DEFAULT NULL,
    PRIMARY KEY (`Customer_id`)
);

CREATE TABLE IF NOT EXISTS ims.item(
    Item_id INT(11) NOT NULL AUTO_INCREMENT,
    Item_name VARCHAR(50) NULL DEFAULT NULL,
    Price DOUBLE NULL DEFAULT NULL,
    Quantity INT NULL DEFAULT NULL,	
    PRIMARY KEY (`Item_id`)
);

CREATE TABLE IF NOT EXISTS ims.order(
    `Order_id` INT NOT NULL AUTO_INCREMENT,
    `FK_Customer_id` INT NOT NULL,
    PRIMARY KEY (`Order_id`),
    FOREIGN KEY (FK_Customer_id) REFERENCES customers (Customer_id) 
);

CREATE TABLE IF NOT EXISTS ims.Order_items(
    Order_ItemsID INT NOT NULL AUTO_INCREMENT,
    FK_Order_id INT NOT NULL,
    FK_Item_id INT NOT NULL,
    Quantity INT NOT NULL,
    Overall_Price Double NOT NULL,
    PRIMARY KEY (`Order_ItemsID`),
    FOREIGN KEY (`FK_Order_id`) REFERENCES `order`(`Order_id`),
    FOREIGN KEY (`FK_Item_id`) REFERENCES item(`Item_id`) 
);