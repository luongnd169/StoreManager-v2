package data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import dao.BillDAO;
import dao.BillDetailDAO;
import dao.CustomerDAO;
import dao.FeeDAO;
import dao.ItemDAO;
import dao.ItemDetailDAO;
import model.Bill;
import model.BillDetail;
import model.Customer;
import model.Fee;
import model.Item;
import model.ItemDetail;

public class Data {
	public static final List<Item> listItem = ItemDAO.getItemes();
	public static final List<ItemDetail> listItemDetail = ItemDetailDAO.getItemDetails();
	public static final List<Fee> listFee = FeeDAO.getFees();
	public static final List<Customer> listPerson = CustomerDAO.getCustomers();
	public static final List<Bill> listBill = BillDAO.getBills();
	public static final List<BillDetail> listBillDetail = BillDetailDAO.getBillDetails();

	public static List<Item> getItemByType(String type) {
		List<Item> temp = new ArrayList<>();
		if (type.equals("Tất cả")) {
			temp.addAll(listItem);
		} else {
			for (Item item : listItem) {
				if (item.getType().equals(type)) {
					temp.add(item);
				}
			}
		}
		return temp;
	}

	public static Item getItemByName(String name) {
		for (Item i : listItem) {
			if (name.equals(i.getName())) {
				return i;
			}
		}
		return null;
	}

	public static int getNextItemId() {
		Item lastItem = listItem.get(listItem.size() - 1);
		return lastItem.getItemId() + 1;
	}

	public static List<ItemDetail> getDetails(int itemId) {
		List<ItemDetail> temp = new ArrayList<>();
		for (ItemDetail detail : listItemDetail) {
			if (detail.getItemId() == itemId) {
				temp.add(detail);
			}
		}
		return temp;
	}

	public static ItemDetail getItemDetailByImei(String imei) {
		for (ItemDetail detail : listItemDetail) {
			if (detail.getImei().equals(imei)) {
				return detail;
			}
		}
		return null;
	}

	public static List<Customer> getCustomer(boolean provider) {
		List<Customer> temp = new ArrayList<>();
		for (Customer c : listPerson) {
			if (provider) {
				if (c.isProvider()) {
					temp.add(c);
				}
			} else {
				if (!c.isProvider()) {
					temp.add(c);
				}
			}
		}
		return temp;
	}

	public static Customer getCustomerByPhone(String phone) {
		for (Customer c : listPerson) {
			if (c.getPhone().equals(phone)) {
				return c;
			}
		}
		return null;
	}

	public static Customer getCustomerByName(String name, boolean provider) {
		for (Customer c : listPerson) {
			if (provider) {
				if (c.getName().equals(name) && provider) {
					return c;
				}
			} else {
				if (c.getName().equals(name) && !provider) {
					return c;
				}
			}
		}
		return null;

	}

	public static Customer getCustomer(int id, boolean provider) {
		for (Customer c : listPerson) {
			if (provider) {
				if (c.getCustomerId() == id && provider) {
					return c;
				}
			} else {
				if (c.getCustomerId() == id && !provider) {
					return c;
				}
			}
		}
		return null;
	}

	public static String getNextBill(String type) {
		String nextBill = "";
		String billNo = "";
		int index = 0;
		switch (type) {
		case "X":
			if (!getSaleBill().isEmpty()) {
				billNo = getSaleBill().get(getSaleBill().size() - 1).getBillNo();
				index = Integer.parseInt(billNo.substring(1));
			} else {
				index = 0;
			}
			nextBill = "X" + analyze(index);
			break;

		case "N":
			if (!getPurchaseBill().isEmpty()) {
				billNo = getPurchaseBill().get(getPurchaseBill().size() - 1).getBillNo();
				index = Integer.parseInt(billNo.substring(1));
			} else {
				index = 0;
			}
			nextBill = "N" + analyze(index);
			break;
		}
		return nextBill;
	}

	public static List<Bill> getSaleBill() {
		List<Bill> temp = new ArrayList<>();
		for (Bill bill : listBill) {
			if (bill.getBillNo().contains("X")) {
				temp.add(bill);
			}
		}
		return temp;
	}

	public static List<Bill> getPurchaseBill() {
		List<Bill> temp = new ArrayList<>();
		for (Bill bill : listBill) {
			if (bill.getBillNo().contains("N")) {
				temp.add(bill);
			}
		}
		return temp;
	}

	public static String analyze(int index) {
		if (index < 10) {
			return "0000" + (index + 1);
		} else if (index < 100) {
			return "000" + (index + 1);
		} else if (index < 1000) {
			return "00" + (index + 1);
		} else if (index < 10000) {
			return "0" + (index + 1);
		} else if (index < 100000) {
			return "" + (index + 1);
		}
		return null;
	}

	public static boolean updateItemDetail(ItemDetail detail) {
		for (ItemDetail id : listItemDetail) {
			if (detail.getId() == id.getId()) {
				id = detail;
				return true;
			}
		}
		return false;
	}

	public static List<ItemDetail> getItemDetailByStatus(int itemId, boolean status) {
		List<ItemDetail> temp = new ArrayList<>();
		for (ItemDetail detail : listItemDetail) {
			if (status) {
				if (detail.getItemId() == itemId && detail.isStatus()) {
					temp.add(detail);
				}
			} else {
				if (detail.getItemId() == itemId && !detail.isStatus()) {
					temp.add(detail);
				}
			}
		}

		return temp;
	}

	public static Item getItem(int itemId) {
		for (Item item : listItem) {
			if (item.getItemId() == itemId) {
				return item;
			}
		}
		return null;
	}

	public static int getPersonId(String name, boolean provider) {
		int id = 0;
		for (Customer c : listPerson) {
			if (provider) {
				if (c.getName().equals(name) && provider) {
					id = c.getCustomerId();
				}
			}
		}

		return id;
	}

	public static boolean updateItem(Item item) {
		for (Item i : listItem) {
			if (item.getItemId() == i.getItemId()) {
				i.setPrice(item.getPrice());
				i.setQuantity(i.getQuantity());
				return true;
			}
		}
		return false;
	}

	public static List<String> getTypes() {
		String type = "";
		for (Item i : listItem) {
			if (isContainType(type, i.getType())) {
				type += "-" + i.getType();
			}
		}
		StringTokenizer st = new StringTokenizer(type, "-");
		List<String> types = new ArrayList<>();
		while (st.hasMoreTokens()) {
			types.add(st.nextToken());
		}
		return types;
	}

	public static boolean isContainType(String types, String type) {
		StringTokenizer st = new StringTokenizer(types, "-");
		boolean check = true;
		while (st.hasMoreTokens()) {
			if (type.equals(st.nextToken())) {
				check = false;
				break;
			} else {
				check = true;
			}
		}
		return check;
	}

	public static String getNextImei(String itemName) {
		int imei = 0;
		for (Item i : listItem) {
			if (i.getName().equals(itemName)) {
				for (ItemDetail id : listItemDetail) {
					if (i.getItemId() == id.getItemId()) {
						if (Integer.parseInt(id.getImei()) > imei) {
							imei = Integer.parseInt(id.getImei());
						}
					}
				}
			}
		}

		return String.valueOf(imei + 1);
	}

	public static List<Fee> getFeesBefore(String date) {
		List<Fee> list = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date to = sdf.parse(date);
			for (Fee f : listFee) {
				if (f.getDate().before(to)) {
					list.add(f);
				}
			}
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Dữ liệu sai");
		}

		return list;
	}

	public static List<Fee> getFees(String from, String to) {
		List<Fee> list = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dFrom = sdf.parse(from);
			Date dTo = sdf.parse(to);
			for (Fee f : listFee) {
				if (sdf.parse(sdf.format(f.getDate())).equals(dFrom)
						|| sdf.parse(sdf.format(f.getDate())).equals(dTo)) {
					list.add(f);
				} else if (f.getDate().after(dFrom) && f.getDate().before(dTo)) {
					list.add(f);
				}
			}
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Dữ liệu sai");
		}

		return list;
	}

	public static void main(String[] args) {

	}
}
