package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Fee {
	private Integer feeId;
	private String name;
	private String value;
	private boolean type;
	private Date date;

	public Fee() {
	}
	
	@Id
	@GeneratedValue
	public Integer getFeeId() {
		return feeId;
	}



	public void setFeeId(Integer feeId) {
		this.feeId = feeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}

}
