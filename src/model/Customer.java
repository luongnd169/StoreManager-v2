package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Customer {
	private Integer customerId;
	private String name;
	private String phone;
	private String address;
	private boolean provider;

	public Customer() {
	}

	@Id
	@GeneratedValue
	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isProvider() {
		return provider;
	}

	public void setProvider(boolean provider) {
		this.provider = provider;
	}

}
