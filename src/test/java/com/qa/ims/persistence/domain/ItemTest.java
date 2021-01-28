package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ItemTest {

	private Item item;
	private Item other;

	@Before
	public void setUp() {
		item = new Item(1L, "table", 50L, 20L);
		other = new Item(1L, "table", 50L, 20L);
	}

	@Test
	public void settersTest() {
		assertNotNull(item.getItem_id());
		assertNotNull(item.getItem_name());
		assertNotNull(item.getPrice());
		assertNotNull(item.getStock());

		item.setItem_id(null);
		assertNull(item.getItem_id());
		item.setItem_name(null);
		assertNull(item.getItem_name());
		item.setPrice(null);
		assertNull(item.getPrice());
		item.setStock(null);
		assertNull(item.getStock());

	}

	@Test
	public void equalsWithNull() {
		assertFalse(item.equals(null));
	}

	@Test
	public void equalsWithDifferentObject() {
		assertFalse(item.equals(new Object()));
	}

	@Test
	public void createItemWithId() {
		assertEquals(1, item.getItem_id(), 0);
		assertEquals("table", item.getItem_name());
		assertEquals(50, item.getPrice(), 0);
		assertEquals(20, item.getStock(), 0);
	}

	@Test
	public void checkEquality() {
		assertTrue(item.equals(item));
	}

	@Test
	public void checkEqualityBetweenDifferentObjects() {
		assertTrue(item.equals(other));
	}

	@Test
	public void nullPriceOnBoth() {
		item.setPrice(null);
		other.setPrice(null);
		assertTrue(item.equals(other));
	}

	@Test
	public void Item_nameNullButOtherNameNotNull() {
		item.setItem_name(null);
		assertFalse(item.equals(other));
	}

	@Test
	public void nullItem_id() {
		item.setItem_id(null);
		assertFalse(item.equals(other));
	}

	@Test
	public void nullItem_idOnBoth() {
		item.setItem_id(null);
		other.setItem_id(null);
		assertTrue(item.equals(other));
	}

	@Test
	public void nullItem_nameOnBoth() {
		item.setItem_name(null);
		other.setItem_name(null);
		assertTrue(item.equals(other));
	}

	@Test
	public void Item_namesNotEqual() {
		other.setItem_name("chair");
		assertFalse(item.equals(other));
	}

	@Test
	public void nullPrice() {
		item.setPrice(null);
		assertFalse(item.equals(other));
	}

	@Test
	public void otherItem_idDifferent() {
		other.setItem_id(2L);
		assertFalse(item.equals(other));
	}

	@Test
	public void otherPriceDifferent() {
		other.setPrice(1L);
		assertFalse(item.equals(other));
	}

	@Test
	public void nullStock() {
		item.setStock(null);
		assertFalse(item.equals(other));
	}

	@Test
	public void nullStockOnBoth() {
		item.setStock(null);
		other.setStock(null);
		assertTrue(item.equals(other));
	}

	@Test
	public void otherStockDifferent() {
		other.setStock(51L);
		assertFalse(item.equals(other));
	}

	@Test
	public void hashCodeTest() {
		assertEquals(item.hashCode(), other.hashCode());
	}

	@Test
	public void hashCodeTestWithNull() {
		Item item = new Item(0L, null, 0L);
		Item other = new Item(0L, null, 0L);
		assertEquals(item.hashCode(), other.hashCode());
	}

	@Test
	public void toStringTest() {
		String toString = "Item [Item_id:1, Item_name:table, Price £:50, Stock_Count:20]";
		assertEquals(toString, item.toString());
	}

}