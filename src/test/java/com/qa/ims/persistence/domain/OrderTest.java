package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class OrderTest {

	private Order order;
	private Order other;

	@Before
	public void setUp() {
		order = new Order(1L, 1L, 2L, 20L, 60L);
		other = new Order(1L, 1L, 2L, 20L, 60L);
	}

	@Test
	public void settersTest() {
		assertNotNull(order.getCustomer_id());

		assertNotNull(order.getOrder_id());
		assertNotNull(order.getItem_id());
		assertNotNull(order.getQuantity());
		assertNotNull(order.getPriceSum());

		order.setCustomer_id(null);
		assertNull(order.getCustomer_id());
		order.setOrder_id(null);
		assertNull(order.getOrder_id());
		order.setItem_id(null);
		assertNull(order.getItem_id());
		order.setQuantity(null);
		assertNull(order.getQuantity());
		order.setPriceSum(null);
		assertNull(order.getPriceSum());
	}

	@Test
	public void equalsWithNull() {
		assertFalse(order.equals(null));
	}

	@Test
	public void equalsWithDifferentObject() {
		assertFalse(order.equals(new Object()));
	}

	@Test
	public void createOrderWithid() {
		assertEquals(1L, order.getOrder_id(), 0);
		assertEquals(1L, order.getCustomer_id(), 0);
		assertEquals(60L, order.getPriceSum(), 0);
		assertEquals(2L, order.getItem_id(), 0);
		assertEquals(20L, order.getQuantity(), 0);
	}

	@Test
	public void checkEqualityBetweenDifferentObjects() {
		assertTrue(order.equals(other));
	}

	@Test
	public void checkEquality() {
		assertTrue(order.equals(order));
	}

	@Test
	public void nullCustomer_id() {
		order.setCustomer_id(null);
		;
		assertFalse(order.equals(other));
	}

	@Test
	public void nullCustomer_idOnBoth() {
		order.setCustomer_id(null);
		other.setCustomer_id(null);
		assertTrue(order.equals(other));
	}

	@Test
	public void otherCustomer_idDifferent() {
		other.setCustomer_id(2L);
		assertFalse(order.equals(other));
	}

	@Test
	public void nullOrder_id() {
		order.setOrder_id(null);
		assertFalse(order.equals(other));
	}

	@Test
	public void nullOrder_idOnBoth() {
		order.setOrder_id(null);
		other.setOrder_id(null);
		assertTrue(order.equals(other));
	}

	@Test
	public void otherOrder_idDifferent() {
		other.setOrder_id(2L);
		assertFalse(order.equals(other));
	}

	@Test
	public void nullItem_id() {
		order.setItem_id(null);
		assertFalse(order.equals(other));
	}

	@Test
	public void nullItem_idOnBoth() {
		order.setItem_id(null);
		other.setItem_id(null);
		assertTrue(order.equals(other));
	}

	@Test
	public void otherItem_idDifferent() {
		other.setItem_id(5L);
		assertFalse(order.equals(other));
	}

	@Test
	public void nullQuantity() {
		order.setQuantity(null);
		assertFalse(order.equals(other));
	}

	@Test
	public void nullQuantityOnBoth() {
		order.setQuantity(null);
		other.setQuantity(null);
		assertTrue(order.equals(other));
	}

	@Test
	public void otherQuantityDifferent() {
		other.setQuantity(30L);
		assertFalse(order.equals(other));
	}

	@Test
	public void nullPriceSum() {
		order.setPriceSum(30L);
		assertFalse(order.equals(other));
	}

	@Test
	public void nullPriceSumOnBoth() {
		order.setPriceSum(0L);
		other.setPriceSum(0L);
		assertTrue(order.equals(other));
	}

	@Test
	public void otherPriceSumDifferent() {
		other.setPriceSum(20L);
		assertFalse(order.equals(other));
	}

	@Test
	public void toStringTest() {
		String toString = "Order [Order_id=1, Customer_id =1, Item_id=2, Quantity=20, PriceSum= £60]";
		assertEquals(toString, order.toString());
	}

	@Test
	public void hashCodeTest() {
		assertEquals(order.hashCode(), other.hashCode());
	}

	@Test
	public void hashCodeTestWithNull() {
		Order order = new Order(0L, 0L, 0L, 0L, 0L);
		Order other = new Order(0L, 0L, 0L, 0L, 0L);
		assertEquals(order.hashCode(), other.hashCode());
	}

}