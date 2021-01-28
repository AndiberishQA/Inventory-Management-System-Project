package com.qa.ims.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.services.ItemServices;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {

	/**
	 * The thing I want to fake functionality for
	 */
	@Mock
	private ItemServices itemServices;

	/**
	 * Spy is used because i want to mock some methods inside the item I'm testing
	 * InjectMocks uses dependency injection to insert the mock into the customer
	 * controller
	 */
	@Spy
	@InjectMocks
	private ItemController itemController;

	@Test
	public void readAllTest() {
		ItemController itemController = new ItemController(itemServices);
		List<Item> items = new ArrayList<>();
		items.add(new Item("Chair", 20L, 12L));
		items.add(new Item("Table", 40L, 10L));
		Mockito.when(itemServices.readAll()).thenReturn(items);
		assertEquals(items, itemController.readAll());
	}

	@Test
	public void createTest() {

		String Item_name = "Chair";
		Long Price = 20L;
		Long Stock = 12L;
		Mockito.doReturn(Item_name, Price, Stock).when(itemController).getInput();
		Item item = new Item(1L, Item_name, 20L, 12L);
		Item insertedItem = new Item(1L, "Chair", 20L, 12L);
		Mockito.when(itemServices.create(item)).thenReturn(insertedItem);
		assertEquals(insertedItem, itemController.create());

	}

	/**
	 * 
	 */
	@Test
	public void updateTest() {

		String Item_name = "Desk";
		Long Price = 25L;
		Long Stock = 5L;

		Mockito.doReturn(Item_name, Price, Stock).when(itemController).getInput();
		Item item = new Item("Desk", 25L, 5L);
		Mockito.when(itemServices.update(item)).thenReturn(item);
		assertEquals(item, itemController.update());
	}

	/**
	 * Delete doesn't return anything, so we can just verify that it calls the
	 * delete method
	 */
	@Test
	public void deleteTest() {
		String Item_id = "1";
		Mockito.doReturn(Item_id).when(itemController).getInput();
		itemController.delete();
		Mockito.verify(itemServices, Mockito.times(1)).delete(1L);
	}

}