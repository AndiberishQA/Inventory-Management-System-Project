package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.services.CrudServicesModifiedOrder;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	private CrudServicesModifiedOrder<Order> orderService;

	public OrderController(CrudServicesModifiedOrder<Order> orderService) {
		this.orderService = orderService;
	}

	String getInput() {
		return Utils.getInput();
	}

	@Override
	public Order create() {
		LOGGER.info("Please enter a ID for Customer");
		Long Customer_id = Long.valueOf(getInput());
		Order Order = orderService.create(new Order(Customer_id));
		LOGGER.info("Please enter a ID for the ordered item");
		Long Item_id = Long.valueOf(getInput());
		LOGGER.info("Please enter the quantity of the item ordered");
		Long Quantity = Long.valueOf(getInput());
		Order = orderService.createOrderLine(new Order(Item_id, Quantity));
		LOGGER.info("Order has been created");
		return Order;
	}

	@Override
	public List<Order> readAll() {
		List<Order> orders = orderService.readAll();
		for (Order order : orders) {
			LOGGER.info(order.toString());
		}
		return orders;
	}

	@Override
	public Order update() {
		LOGGER.info("Please enter customer ID");
		Long Customer_id = Long.valueOf(getInput());
		LOGGER.info("Please enter the ID of the order you would like to update");
		Long Order_id = Long.valueOf(getInput());
		Order order = new Order(Order_id, Customer_id);

		LOGGER.info("What would you like to do with this order?");
		UpdateOrderFunction.printUpdateOrderFunction();
		UpdateOrderFunction function = UpdateOrderFunction.getUpdateOrderFunctions();

		Long Item_id;
		Long Quantity;

		switch (function) {
		case ADD:
			LOGGER.info("Please enter ID of item you want to add");
			Item_id = Long.valueOf(getInput());
			LOGGER.info("Please enter quantity of item ordered");
			Quantity = Long.valueOf(getInput());
			order = orderService.order_itemsUpdateAdd(order, Item_id, Quantity);
			LOGGER.info("Order Updated");
			return order;
		case DEL:
			LOGGER.info("Please enter ID of item you want to delete");
			Item_id = Long.valueOf(getInput());
			order = orderService.order_itemsUpdateDelete(order, Item_id);
			LOGGER.info("Order Updated");
			return order;
		case STOP:
			break;
		}
		return order;

	}

	@Override
	public void delete() {
		LOGGER.info("Please enter the ID of the order you would like to delete");
		Long Order_id = Long.valueOf(getInput());
		orderService.delete(Order_id);
		LOGGER.info("Order has been deleted");
	}

}
