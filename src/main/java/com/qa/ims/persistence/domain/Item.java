package com.qa.ims.persistence.domain;

public class Item {

	private Long Item_id;
	private String Item_name;
	private Long Price;
	private Long Quantity;

	public Item(String Item_name, Long Price, Long Quantity) {
		this.Item_name = Item_name;
		this.Price = Price;
		this.Quantity = Quantity;
	}

	public Item(Long item_id, String item_name, Long Price, Long Quantity) {
		super();
		this.Item_id = item_id;
		this.Item_name = item_name;
		this.Price = Price;
		this.Quantity = Quantity;
	}

	public long getItem_id() {
		return Item_id;
	}

	public void setItem_id(Long item_id) {
		this.Item_id = item_id;
	}

	public String getItem_name() {
		return Item_name;
	}

	public void setItem_name(String item_name) {
		this.Item_name = item_name;
	}

	public Long getPrice() {
		return Price;
	}

	public void setPrice(Long Price) {
		this.Price = Price;
	}

	public Long getQuantity() {
		return Quantity;
	}

	public void setQuantity(Long Quantity) {
		this.Quantity = Quantity;
	}

	public String toString() {
		return "Item [Item_id=" + Item_id + ", Item_name=" + Item_name + ", Price=" + Price + ", Quantity=" + Quantity
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (Item_id ^ (Item_id >>> 32));
		result = prime * result + ((Item_name == null) ? 0 : Item_name.hashCode());
		result = prime * result + (int) (Price ^ (Price >>> 32));
		result = prime * result + (int) (Quantity ^ (Quantity >>> 32));
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
		if (Item_id != other.Item_id)
			return false;
		if (Item_name == null) {
			if (other.Item_name != null)
				return false;
		} else if (!Item_name.equals(other.Item_name))
			return false;
		if (Price != other.Price)
			return false;
		if (Quantity != other.Quantity)
			return false;
		return true;
	}

}