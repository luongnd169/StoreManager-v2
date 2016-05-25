package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Bill {
	private Integer billId;
	private String billNo;
	private String totalPrice;
	private String date;
	private String customerPhone;

	public Bill() {

	}

	@Id
	@GeneratedValue
	public Integer getBillId() {
		return billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	@Override
	public String toString() {
		return "Bill [billId=" + billId + ", billNo=" + billNo + ", totalPrice=" + totalPrice + ", date=" + date
				+ ", customerPhone=" + customerPhone + "]";
	}
	
	

}
