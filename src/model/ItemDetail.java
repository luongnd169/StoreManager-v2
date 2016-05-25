package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ItemDetail {
	private Integer id;
	private Integer itemId;
	private String importPrice;
	private String imei;
	private Integer provider;
	private String importDate;
	private boolean status;
	private String exportDate;
	private Integer customer;

	public ItemDetail() {
	}

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getImportPrice() {
		return importPrice;
	}

	public void setImportPrice(String importPrice) {
		this.importPrice = importPrice;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public Integer getProvider() {
		return provider;
	}

	public void setProvider(Integer provider) {
		this.provider = provider;
	}

	public String getImportDate() {
		return importDate;
	}

	public void setImportDate(String importDate) {
		this.importDate = importDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getExportDate() {
		return exportDate;
	}

	public void setExportDate(String exportDate) {
		this.exportDate = exportDate;
	}

	public Integer getCustomer() {
		return customer;
	}

	public void setCustomer(Integer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "ItemDetail [id=" + id + ", itemId=" + itemId + ", importPrice=" + importPrice + ", imei=" + imei
				+ ", provider=" + provider + ", importDate=" + importDate + ", status=" + status + ", exportDate="
				+ exportDate + ", customer=" + customer + "]";
	}
	
	

}
