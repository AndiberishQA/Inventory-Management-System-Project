package com.qa.ims.persistence.domain;

import java.util.ArrayList;
import java.util.List;

public class Order {

	private Long Order_id;
	private Long Customer_id;
	private List<Item> Item_id = new ArrayList<>();
	private Long Quantity;

	// constructors
	public Order(Long id, Long customer_id) {
		super();
		this.Order_id = id;
		this.Customer_id = customer_id;
	}

	public Order(Long customer_id) {
		super();
		this.Customer_id = customer_id;
	}

	public Order(Long id, List<Item> item_id) {
		super();
		this.Order_id = id;
		this.Item_id = item_id;
	}

	public Order(Long id, Long customer_id, List<Item> item_id) {
		super();
		this.Order_id = id;
		this.Customer_id = customer_id;
		this.Item_id = item_id;
	}

	public Order(Long id, Long customer_id, List<Item> items, Long quantity) {
		super();
		this.Order_id = id;
		this.Customer_id = customer_id;
		this.Item_id = items;
		this.Quantity = quantity;

	}

	// Generate getters & setters
	public long getOrder_Id() {
		return Order_id;
	}

	public void setOrder_Id(Long id) {
		this.Order_id = id;
	}

	public long getCustomerId() {
		return Customer_id;
	}

	public void setCustomerId(Long customer_id) {
		this.Customer_id = customer_id;
	}

	public List<Item> getItems() {
		return Item_id;
	}

	public void setItems(List<Item> items) {
		this.Item_id = items;
	}

	public Long getQuantity() {
		return Quantity;
	}

	public void setQuantity(Long quantity) {
		Quantity = quantity;
	}

	@Override
	public String toString() {
		return "Order [Order_id=" + Order_id + ", Customer_id=" + Customer_id + ", Item_id=" + Item_id + ", Quantity="
				+ Quantity + "]";
	}

}