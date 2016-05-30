package data;

import java.util.ArrayList;
import java.util.List;

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

	public static void main(String[] args) {
		System.out.println(getNextItemId());
	}
}
