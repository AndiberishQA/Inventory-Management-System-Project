package com.qa.ims.services;

import java.util.List;

import com.qa.ims.persistence.dao.OrderCustomDaoInterface;
import com.qa.ims.persistence.domain.Order;

public class OrderServices implements CrudServicesModifiedOrder<Order> {

	private OrderCustomDaoInterface<Order> orderCustomDaoInterface;

	public OrderServices(OrderCustomDaoInterface<Order> orderCustomDaoInterface) {
		this.orderCustomDaoInterface = orderCustomDaoInterface;
	}

	@Override
	public List<Order> readAll() {
		return orderCustomDaoInterface.readAll();
	}

	@Override
	public Order create(Order order) {
		return orderCustomDaoInterface.create(order);
	}

	@Override
	public Order order_itemsUpdateDelete(Order order, Long item_id) {
		return orderCustomDaoInterface.order_itemsUpdateDelete(order, item_id);
	}

	@Override
	public Order order_itemsUpdateAdd(Order order, Long item_id, Long quantity) {
		return orderCustomDaoInterface.order_itemsUpdateAdd(order, item_id, quantity);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

}