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

import com.qa.ims.persistence.domain.Item;

public class ItemDaoMysql implements Dao<Item> {

	public static final Logger LOGGER = LogManager.getLogger(ItemDaoMysql.class);

	private String jdbcConnectionUrl;
	private String username;
	private String password;

	public ItemDaoMysql(String username, String password) {
		this.jdbcConnectionUrl = "jdbc:mysql://localhost:3306/ims";
		this.username = username;
		this.password = password;

	}

	public ItemDaoMysql(String jdbcConnectionUrl, String username, String password) {

		this.jdbcConnectionUrl = "jdbc:mysql://localhost:3306/ims";
		this.username = username;
		this.password = password;
	}

	@Override
	public Item domainFromResultSet(ResultSet resultSet) throws SQLException {
		Long Item_id = resultSet.getLong("Item_id");
		String Item_Name = resultSet.getString("Item_Name");
		Long Price = resultSet.getLong("Price");
		Long Quantity = resultSet.getLong("Quantity");
		return new Item(Item_id, Item_Name, Price, Quantity);

	}

//	 The read all function reads all items from database and returns
	// a list of items in our database.

	@Override
	public List<Item> readAll() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from item");) {
			ArrayList<Item> Items = new ArrayList<>();
			while (resultSet.next()) {
				Items.add(domainFromResultSet(resultSet));
			}
			return Items;
		} catch (SQLException e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

//	*Reads the latest entry in the item database
	public Item readLatest() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM item ORDER BY Item_id DESC LIMIT 1");) {
			resultSet.next();
			return domainFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());

		}
		return null;

	}

	@Override
	public Item create(Item item) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("insert into item(Item_name, Price, Quantity) values('" + item.getItem_name()
					+ "','" + item.getPrice() + "','" + item.getQuantity() + "')");
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

//	This method allows you to select a specific item to view by entering its item id.
	public Item ReadItem(Long Item_id) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT FROM item where Item id = " + Item_id);) {
			resultSet.next();
			return domainFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.info(e.getMessage());
		}

		return null;
	}

	@Override
	public Item update(Item item) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("update item set Item_name ='" + item.getItem_name() + "', Price ='"
					+ item.getPrice() + "', Quantity ='" + item.getQuantity() + "' where id =" + item.getItem_id());
			return ReadItem(item.getItem_id());
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;

	}

	@Override
	public void delete(long Item_id) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("delete from item where item id = " + Item_id);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}

	}

	public void Iterate_Item(Long Item_id) {
		Item item = ReadItem(Item_id);
		item.setQuantity(item.getQuantity() - 1);
		update(item);
	}

}