package com.qa.ims.persistence.domain;

public class Customer {

	private Long Customer_id;
	private String first_name;
	private String surname;

	public Customer(String first_name, String surname) {
		this.first_name = first_name;
		this.surname = surname;
	}

	public Customer(Long customer_id, String first_name, String surname) {
		this.Customer_id = customer_id;
		this.first_name = first_name;
		this.surname = surname;
	}

	public Long getCustomer_id() {
		return Customer_id;
	}

	public void setCustomer_id(Long customer_id) {
		this.Customer_id = customer_id;
	}

	public String getfirst_name() {
		return first_name;
	}

	public void setfirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String toString() {
		return "Customer_id:" + Customer_id + " first_name:" + first_name + " surname:" + surname;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first_name == null) ? 0 : first_name.hashCode());
		result = prime * result + ((Customer_id == null) ? 0 : Customer_id.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
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
		Customer other = (Customer) obj;
		if (first_name == null) {
			if (other.first_name != null)
				return false;
		} else if (!first_name.equals(other.first_name))
			return false;
		if (Customer_id == null) {
			if (other.Customer_id != null)
				return false;
		} else if (!Customer_id.equals(other.Customer_id))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

}
