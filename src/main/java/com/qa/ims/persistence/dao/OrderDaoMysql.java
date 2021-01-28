package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Order;

public class OrderDaoMysql implements OrderCustomDaoInterface<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	private String jdbcConnectionUrl;
	private String username;
	private String password;

	public OrderDaoMysql(String username, String password) {
		this.jdbcConnectionUrl = "jdbc:mysql://localhost:3306/ims";
		this.username = username;
		this.password = password;

	}

	public OrderDaoMysql(String jdbcConnectionUrl, String username, String password) {
		this.jdbcConnectionUrl = "jdbc:mysql://localhost:3306/ims";
		this.username = username;
		this.password = password;
	}

	@Override
	public Order domainFromResultSet(ResultSet resultSet) throws SQLException {
		Long Order_id = resultSet.getLong("Order_id");
//		Long Customer_id = resultSet.getLong("Customer_id");
//		Long Item_id = resultSet.getLong("Item_id");
//		Long Quantity = resultSet.getLong("Quantity");
//		Long PriceSum = resultSet.getLong("Sum(order_items.PriceSum");

		return new Order(Order_id);
	}

	public Order domainFromResultSetRA(ResultSet resultSet) throws SQLException {
		Long Order_id = resultSet.getLong("Order_id");
		Long Customer_id = resultSet.getLong("Customer_id");
		Long Item_id = resultSet.getLong("Item_id");
		Long Quantity = resultSet.getLong("Quantity");
		Long PriceSum = resultSet.getLong("Overall_Price");

		return new Order(Order_id, Customer_id, Item_id, Quantity, PriceSum);
	}
	// Create Order

	@Override
	public Order create(Order order) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
//			

			String EstablishOrder = "INSERT INTO `order`(Customer_id) VALUES(" + order.getCustomer_id() + ");";
			statement.executeUpdate(EstablishOrder);

			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	// Read Orders

	public Order readOrder(Long Order_id) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery("SELECT order_items.Order_id,order.Customer_id,SUM(order_items.Overall_Price) "
								+ "from order_items JOIN order ON order.Order_id=order_items.Order_id "
								+ "JOIN item ON item.Item_id=order_items.Item_id WHERE order.order_id=" + Order_id
								+ ";");) {
			resultSet.next();
			return domainFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public Order readLatest() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery("SELECT Order_id from `order` ORDER BY Order_id DESC LIMIT 1");) {

			resultSet.next();

			return domainFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Order> readAll() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(

						"select * from `order` o JOIN order_items oi ON o.order_id=oi.order_id");) {
			List<Order> order = new ArrayList<>();
			while (resultSet.next()) {
				order.add(domainFromResultSetRA(resultSet));
			}
			return order;
		} catch (SQLException e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	@Override
	public Order order_itemsUpdateAdd(Order order, Long item_id, Long quantity) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement()) {
			ResultSet resultSetPrice = statement.executeQuery("SELECT Price FROM item WHERE Item_id=" + item_id);

			Long updatedPrice = null;
			while (resultSetPrice.next()) {
				updatedPrice = resultSetPrice.getLong("Price") * quantity;
			}

			statement.executeUpdate("INSERT INTO order_items(Order_id,Item_id,Quantity,Overall_Price) VALUES("
					+ order.getOrder_id() + "," + item_id + "," + quantity + "," + updatedPrice + ");");
			return readOrder(order.getOrder_id());
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Order order_itemsUpdateDelete(Order order, Long item_id) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate(
					"DELETE FROM order_items WHERE Order_id=" + order.getOrder_id() + " AND item_id=" + item_id + ";");
			return readOrder(order.getOrder_id());
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public void delete(Long Order_id) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("delete from order_items where Order_id = " + Order_id);
			statement.executeUpdate("delete from `order` where Order_id = " + Order_id);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}

	}

	public Order readOrderline() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery("SELECT * from order_items ORDER by Order_id DESC LIMIT 1");) {

			resultSet.next();

			return domainFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Order createOrderLine(Order order) {

		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			ResultSet resultSetPrice = statement
					.executeQuery("SELECT Price FROM item WHERE Item_id=" + order.getItem_id());
			while (resultSetPrice.next()) {
				Long Price = resultSetPrice.getLong("Price");
				order.setPriceSum(Price);
			}
			Long SummaryPrice = order.getQuantity() * order.getPriceSum();
			order.setPriceSum(SummaryPrice);

			String EstablishOrder = "INSERT INTO order_items(Order_id,Item_id,Quantity,Overall_Price) VALUES((SELECT Order_id FROM `order` ORDER BY Order_id DESC LIMIT 1),"
					+ order.getItem_id() + "," + order.getQuantity() + "," + order.getPriceSum() + ");";

			statement.executeUpdate(EstablishOrder);

			return readOrderline();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

}