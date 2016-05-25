package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Item {
	private Integer itemId;
	private String name;
	private String type;
	private String price;
	private Integer quantity;

	public Item() {

	}

	@Id
	@GeneratedValue
	@Column(name = "itemId", unique = true, nullable = false)
	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", name=" + name + ", type=" + type + ", price=" + price + ", quantity="
				+ quantity + "]";
	}
	
	

}
