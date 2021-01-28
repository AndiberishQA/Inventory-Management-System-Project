package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.Ims;
import com.qa.ims.persistence.domain.Customer;

public class CustomerDaoMysqlTest {

	private static final Logger LOGGER = LogManager.getLogger(CustomerDaoMysql.class);
	private static final String jdbcConnectionUrl = "jdbc:mysql://localhost:3306/ims_testDB";
	private static final String username = "root";
	private static final String password = "root";

	@BeforeClass
	public static void init() {
		Ims ims = new Ims();
		ims.init(jdbcConnectionUrl, username, password, "src/test/resources/sql-schema.sql");
	}

	@Before
	public void setUp() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("delete from imstest.customers");
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());

		}
	}

//	@Test
//	public void createTest() {
//		CustomerDaoMysql customerDao = new CustomerDaoMysql();
//		String first_name = "Tom";
//		String surname = "Harry";
//		Customer customer = new Customer(1L, first_name, surname);
//		Customer insertedCustomer = new Customer(1L, first_name, surname);
//		customer = customerDao.create(customer);
//		assertEquals(insertedCustomer, customer);

	@Test
	public void readAllTest() {
		CustomerDaoMysql customerDaoMysql = new CustomerDaoMysql(null, null);
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(1L, "Jordan", "Harris"));
		customers.add(new Customer(2L, "Isaac", "Newton"));
		customerDaoMysql.create(new Customer("Jordan", "Harris"));
		customerDaoMysql.create(new Customer("Isaac", "Newton"));

		assertEquals(customers, customerDaoMysql.readAll());
	}

	@Test
	public void updateTest() {
		CustomerDaoMysql customerDAO = new CustomerDaoMysql(null, null);
		Customer customer = new Customer("Eni", "Ber");
		Customer newCustomer = new Customer(1L, "Tom", "Harry");
		customerDAO.create(customer);

		assertNotEquals(customer, customerDAO.update(newCustomer));
	}

	@Test
	public void deleteTest() {
		CustomerDaoMysql customerDAO = new CustomerDaoMysql(null, null);
		customerDAO.delete(1L);
	}

}
