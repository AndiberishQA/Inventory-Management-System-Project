package com.qa.ims.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.utils.Utils;

public enum UpdateOrderFunction {

	ADD("Add new item to order"), DEL("Delete item from order"), STOP("To close the application");

	public static final Logger LOGGER = LogManager.getLogger();

	private String description;

	UpdateOrderFunction(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.name() + ": " + this.description;
	}

	public static void printUpdateOrderFunction() {
		for (UpdateOrderFunction function : UpdateOrderFunction.values()) {
			LOGGER.info(function.getDescription());
		}
	}

	public static UpdateOrderFunction getUpdateOrderFunctions() {
		UpdateOrderFunction function = null;
		do {
			try {
				function = UpdateOrderFunction.valueOf(Utils.getInput().toUpperCase());
			} catch (IllegalArgumentException e) {
				LOGGER.error("The selection was incorrect please retry");
			}
		} while (function == null);
		return function;
	}

}
