package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.CustomerDaoMysql;
import com.qa.ims.persistence.dao.ItemDaoMysql;
import com.qa.ims.persistence.dao.OrderDaoMysql;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.services.CrudServices;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger(OrderController.class);

	private CrudServices<Order> orderService;

	private OrderDaoMysql orderDaoMysql;
	private CustomerDaoMysql customerDaoMysql;
	private ItemDaoMysql itemDaoMysql;
	private Utils utils;

	public OrderController(OrderDaoMysql orderDaoMysql, ItemDaoMysql itemDaoMysql, CustomerDaoMysql customerDaoMysql,
			Utils utils) {
		super();
		this.orderDaoMysql = orderDaoMysql;
		this.itemDaoMysql = itemDaoMysql;
		this.customerDaoMysql = customerDaoMysql;
		this.utils = utils;
	}

	public OrderController(CrudServices<Order> orderService) {
		this.orderService = orderService;
	}

	String getInput() {
		return Utils.getInput();
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
	public Order create() {
		List<Customer> customers = customerDaoMysql.readAll();
		for (Customer customer : customers) {
			LOGGER.info(customer.toString());
		}
		LOGGER.info("Enter a customer ID: ");
		long customerID = utils.getLong();
		Order order = orderDaoMysql.create(new Order(customerID));
		String response;
		do {
			List<Item> items = itemDaoMysql.readAll();
			for (Item item : items) {
				LOGGER.info(item.toString());
			}
			LOGGER.info("Please enter the ID of the product");
			long itemID = utils.getLong();
			orderDaoMysql.createItem(order.getItems(), itemID);
			LOGGER.info("Would you like to add another item yes/no?");
			response = utils.getString();
		} while (response.toLowerCase().equals("yes"));
		order = orderDaoMysql.readLatest();
		return order;

	}

	public Order update() {
		LOGGER.info("Enter the order ID you wish to update: ");
		long id = utils.getLong();

		LOGGER.info("Enter a customer ID: ");
		long customerId = utils.getLong();

		Order order = orderDaoMysql.update(new Order(id, customerId));

		LOGGER.info("Would you like to add to or delete items from this order?");
		String input = utils.getString();

		if (input.toLowerCase().equals("delete")) {
			LOGGER.info("Enter an item ID: ");
			Long itemId = utils.getLong();
			orderDaoMysql.deleteLine(id, itemId);
			return order;
		} else if (input.toLowerCase().equals("add")) {
			String answer;
			do {
				LOGGER.info("Enter an item ID: ");
				Long itemId = utils.getLong();
				orderDaoMysql.createItem(order.getId(), itemId);
				LOGGER.info("Add more items to order?");
				answer = utils.getString();
			} while (answer.toLowerCase().equals("yes"));
		} else {
			LOGGER.info("Invalid response, please enter add or delete");
		}
		LOGGER.info("Order Updated");
		return order;

	}

	@Override
	public void delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		long id = utils.getLong();
		orderDaoMysql.deleteOrderLines(id);
		return;
	}

}
