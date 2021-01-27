package com.qa.ims.services;

import java.util.List;

import com.qa.ims.persistence.domain.Order;

public interface CrudServicesModifiedOrder<T> {

	public List<T> readAll();

	T create(T t);

	T createOrderLine(T t);

	Order order_itemsUpdateDelete(Order order, Long item_id);

	Order order_itemsUpdateAdd(Order order, Long item_id, Long quantity);

	void delete(Long id);

}
