package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.services.CrudServices;
import com.qa.ims.utils.Utils;

public class ItemController implements CrudController<Item> {

	public static final Logger LOGGER = LogManager.getLogger(ItemController.class);

	private CrudServices<Item> itemService;

	public ItemController(CrudServices<Item> itemService) {
		this.itemService = itemService;
	}

	String getInput() {
		return Utils.getInput();
	}

	/**
	 * Reads all customers to the logger
	 */
	@Override
	public List<Item> readAll() {
		List<Item> items = itemService.readAll();
		for (Item item : items) {
			LOGGER.info(item.toString());
		}
		return items;
	}

	/**
	 * Creates a customer by taking in user input
	 */
	@Override
	public Item create() {
		LOGGER.info("Please enter the name of the item");
		String Item_name = getInput();
		LOGGER.info("Please enter the Price of the item");
		Long Price = Long.valueOf(getInput());
		LOGGER.info("Please enter the amount of Stock this item has");
		Long Stock = Long.valueOf(getInput());
		Item item = itemService.create(new Item(Item_name, Price, Stock));
		LOGGER.info("Item created");

		// TODO Auto-generated method stub
		return item;
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Item update() {
		LOGGER.info("Please enter the id of the Item you would like to update");
		Long Item_id = Long.valueOf(getInput());
		LOGGER.info("Please enter the new item name");
		String Item_name = getInput();
		LOGGER.info("Please enter the updated Price of the item");
		Long Price = Long.valueOf(getInput());
		LOGGER.info("Please enter the updated Stock amount of the item");
		Long Stock = Long.valueOf(getInput());
		Item item = itemService.update(new Item(Item_id, Item_name, Price, Stock));
		LOGGER.info("Item Updated");
		return item;
	}

	/**
	 * Deletes an existing customer by the id of the customer
	 */

	@Override
	public void delete() {
		LOGGER.info("Please enter the item ID of the item you would like to delete");
		Long Item_id = Long.valueOf(getInput());
		itemService.delete(Item_id);
		LOGGER.info("Item Record has been deleted");

	}

}