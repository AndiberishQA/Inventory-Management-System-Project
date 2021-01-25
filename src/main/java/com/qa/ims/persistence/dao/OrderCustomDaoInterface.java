package com.qa.ims.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.qa.ims.persistence.domain.Order;

public interface OrderCustomDaoInterface<T> {

	List<T> readAll();

	T create(T t);

	Order order_itemsUpdateDelete(Order order, Long item_id);

	Order order_itemsUpdateAdd(Order order, Long item_id, Long quantity);

	void delete(Long id);

	T domainFromResultSet(ResultSet resultSet) throws SQLException;

}