package com.qa.ims.persistence.domain;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Order {

	private long Order_id = 0;
	private long Customer_id = 0;
	private List<Item> items = new ArrayList<>();
	private long TotalOrderCost;

	// constructors
	public Order(long id, long customer_id) {
		super();
		this.Order_id = id;
		this.Customer_id = customer_id;
	}

	public Order(long customer_id) {
		super();
		this.Customer_id = customer_id;
	}

	public Order(long id, List<Item> items) {
		super();
		this.Order_id = id;
		this.items = items;
	}

	public Order(long id, long customer_id, List<Item> items) {
		super();
		this.Order_id = id;
		this.Customer_id = customer_id;
		this.items = items;
	}

	public Order(long id, long customer_id, List<Item> items, Long totalOrderCost) {
		super();
		this.Order_id = id;
		this.Customer_id = customer_id;
		this.items = items;
		this.TotalOrderCost = totalOrderCost;
	}

	// Generate getters & setters
	public long getId() {
		return Order_id;
	}

	public void setId(long id) {
		this.Order_id = id;
	}

	public long getCustomerId() {
		return Customer_id;
	}

	public void setCustomerId(long customer_id) {
		this.Customer_id = customer_id;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public long getTotalOrderCost() {
		return TotalOrderCost;
	}

	public void setTotalOrderCost(long totalOrderCost) {
		this.TotalOrderCost = totalOrderCost;
	}

	@SuppressWarnings("unchecked")
	public static Order convert(ResultSet resultSet) throws SQLException {
		if (resultSet.next()) {
			long Order_id = resultSet.getLong("Order_id");
			long Customer_id = resultSet.getLong("Customer_id");
			Array items = resultSet.getArray("Item_id");
			return new Order(Order_id, Customer_id, (List<Item>) items);
		}

		return null;

	}

	@Override
	public String toString() {
		return "Order id: " + Order_id + ", Customer id: " + Customer_id + ", items: " + items + ", Total Order Cost:"
				+ TotalOrderCost;
	}

}