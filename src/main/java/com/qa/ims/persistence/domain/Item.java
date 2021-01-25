package com.qa.ims.persistence.domain;

public class Item {

	private Long Item_id;
	private String Item_name;
	private Long Price;
	private Long Stock;

	public Item(Long item_id, String item_name) {
		super();
		Item_id = item_id;
		Item_name = item_name;
	}

	public Item(Long item_id, String item_name, Long price) {
		super();
		Item_id = item_id;
		Item_name = item_name;
		Price = price;
	}

	public Item(String item_name, Long price, Long stock) {
		super();
		Item_name = item_name;
		Price = price;
		Stock = stock;
	}

	public Item(Long item_id, String item_name, Long price, Long stock) {
		super();
		Item_id = item_id;
		Item_name = item_name;
		Price = price;
		Stock = stock;
	}

	public Long getItem_id() {
		return Item_id;
	}

	public void setItem_id(Long item_id) {
		Item_id = item_id;
	}

	public String getItem_name() {
		return Item_name;
	}

	public void setItem_name(String item_name) {
		Item_name = item_name;
	}

	public Long getPrice() {
		return Price;
	}

	public void setPrice(Long price) {
		Price = price;
	}

	public Long getStock() {
		return Stock;
	}

	public void setStock(Long stock) {
		Stock = stock;
	}

	@Override
	public String toString() {
		return "Item [Item_id=" + Item_id + ", Item_name=" + Item_name + ", Price=" + Price + ", Stock=" + Stock + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Item_id == null) ? 0 : Item_id.hashCode());
		result = prime * result + ((Item_name == null) ? 0 : Item_name.hashCode());
		result = prime * result + ((Price == null) ? 0 : Price.hashCode());
		result = prime * result + ((Stock == null) ? 0 : Stock.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (Item_id == null) {
			if (other.Item_id != null)
				return false;
		} else if (!Item_id.equals(other.Item_id))
			return false;
		if (Item_name == null) {
			if (other.Item_name != null)
				return false;
		} else if (!Item_name.equals(other.Item_name))
			return false;
		if (Price == null) {
			if (other.Price != null)
				return false;
		} else if (!Price.equals(other.Price))
			return false;
		if (Stock == null) {
			if (other.Stock != null)
				return false;
		} else if (!Stock.equals(other.Stock))
			return false;
		return true;
	}

}
