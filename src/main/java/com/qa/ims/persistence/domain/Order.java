package com.qa.ims.persistence.domain;

public class Order {

	private Long Order_id;
	private Long Customer_id;
	private Long Item_id;
	private Long Quantity;
	private Long PriceSum;

	public Order() {
		super();
	}

	public Order(Long item_id, Long quantity) {
		super();
		Item_id = item_id;
		Quantity = quantity;
	}

	public Order(Long customer_id) {
		super();
		Customer_id = customer_id;
	}

	public Order(Long customer_id, Long item_id, Long quantity) {
		super();
		Customer_id = customer_id;
		Item_id = item_id;
		Quantity = quantity;
	}

	public Order(Long order_id, Long customer_id, Long item_id, Long quantity) {
		super();
		Order_id = order_id;
		Customer_id = customer_id;
		Item_id = item_id;
		Quantity = quantity;
	}

	public Order(Long order_id, Long customer_id, Long item_id, Long quantity, Long priceSum) {
		super();
		Order_id = order_id;
		Customer_id = customer_id;
		Item_id = item_id;
		Quantity = quantity;
		PriceSum = priceSum;
	}

	@Override
	public String toString() {
		return "Order [Order_id=" + Order_id + ", Customer_id=" + Customer_id + ", Item_id=" + Item_id + ", Quantity="
				+ Quantity + ", PriceSum=" + PriceSum + "]";
	}

	public Long getOrder_id() {
		return Order_id;
	}

	public void setOrder_id(Long order_id) {
		Order_id = order_id;
	}

	public Long getCustomer_id() {
		return Customer_id;
	}

	public void setCustomer_id(Long customer_id) {
		Customer_id = customer_id;
	}

	public Long getItem_id() {
		return Item_id;
	}

	public void setItem_id(Long item_id) {
		Item_id = item_id;
	}

	public Long getQuantity() {
		return Quantity;
	}

	public void setQuantity(Long quantity) {
		Quantity = quantity;
	}

	public Long getPriceSum() {
		return PriceSum;
	}

	public void setPriceSum(Long priceSum) {
		PriceSum = priceSum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Customer_id == null) ? 0 : Customer_id.hashCode());
		result = prime * result + ((Item_id == null) ? 0 : Item_id.hashCode());
		result = prime * result + ((Order_id == null) ? 0 : Order_id.hashCode());
		long temp;
		temp = Double.doubleToLongBits(PriceSum);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((Quantity == null) ? 0 : Quantity.hashCode());
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
		Order other = (Order) obj;
		if (Customer_id == null) {
			if (other.Customer_id != null)
				return false;
		} else if (!Customer_id.equals(other.Customer_id))
			return false;
		if (Item_id == null) {
			if (other.Item_id != null)
				return false;
		} else if (!Item_id.equals(other.Item_id))
			return false;
		if (Order_id == null) {
			if (other.Order_id != null)
				return false;
		} else if (!Order_id.equals(other.Order_id))
			return false;
		if (Double.doubleToLongBits(PriceSum) != Double.doubleToLongBits(other.PriceSum))
			return false;
		if (Quantity == null) {
			if (other.Quantity != null)
				return false;
		} else if (!Quantity.equals(other.Quantity))
			return false;
		return true;
	}

}
