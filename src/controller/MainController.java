package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import data.Data;
import gui.Main;
import lib.Convert;
import model.Bill;
import model.BillDetail;
import model.Customer;
import model.Item;
import model.ItemDetail;

public class MainController {

	Main main;

	public MainController() {

	}

	// public void addItemToList(List<Item> listItem, String name) {
	// listItem.add(ItemDAO.getItem("FROM Item where name = '" + name +
	// "'").get(0));
	// }

	public static void saveBill(List<Item> listItem, List<ItemDetail> listItemDetail, Customer c, String type) {
		String nextBill = Data.getNextBill(type);
		List<Item> list = new ArrayList<>();
		list.addAll(Data.listItem);
		List<Item> temp = new ArrayList<>();
		temp.addAll(listItem);
		Bill bill = new Bill();
		bill.setBillNo(nextBill);
		int totalPrice = 0;
		for (Item i : listItem) {
			totalPrice += Integer.parseInt(i.getPrice()) * i.getQuantity();
		}
		bill.setDate(Convert.formatDateSQL(new Date()));
		bill.setTotalPrice(totalPrice + "");
		bill.setCustomerPhone(c.getPhone());
		Data.listBill.add(bill);
		BillDetail detail = new BillDetail();
		detail.setBillNo(nextBill);
		detail.setDate(bill.getDate());
		for (Item i : listItem) {
			detail.setName(i.getName());
			detail.setPrice(i.getPrice());
			detail.setQuantity(i.getQuantity());
			Data.listBillDetail.add(detail);
		}
		for (Item i1 : list) {
			for (Item i2 : listItem) {
				if (i1.getItemId().equals(i2.getItemId())) {
					if (type.equals("X")) {
						i1.setQuantity(i1.getQuantity() - i2.getQuantity());
					} else {
						i1.setQuantity(i1.getQuantity() + i2.getQuantity());
					}
				}
			}
		}
		for (Item i1 : list) {
			for (Item i2 : temp) {
				if (i1.getItemId().equals(i2.getItemId())) {
					i2.setQuantity(i1.getQuantity());
				}
			}
		}
		for (ItemDetail id : listItemDetail) {
			if (type.equals("X")) {
				id.setCustomer(c.getCustomerId());
				id.setStatus(false);
				id.setExportDate(Convert.formatDateSQL(new Date()));
				Data.updateItemDetail(id);
			} else {
				id.setProvider(c.getCustomerId());
				id.setStatus(true);
				id.setImportDate(Convert.formatDateSQL(new Date()));
				Data.listItemDetail.add(id);
			}

		}
		for (Item i : temp) {
			int updatePrice = 0;
			List<ItemDetail> listDetail = Data.getItemDetailByStatus(i.getItemId(), true);
			if (!listDetail.isEmpty()) {
				for (ItemDetail id : listDetail) {
					updatePrice += Integer.parseInt(id.getImportPrice());
				}
				i.setPrice(String.valueOf(updatePrice / listDetail.size()));
			}

		}
		for (Item i : temp) {
			if (!Data.updateItem(i)) {
				if (type.equals("N")) {
					Data.listItem.add(i);
				}
			}
		}
	}

	public List<Item> searchItemByName(List<Item> listItem, String name) {
		List<Item> temp = new ArrayList<>();
		for (Item i : listItem) {
			if (containsOf(i.getName().toLowerCase().trim(), name.toLowerCase().trim())) {
				temp.add(i);
			}
		}
		return temp;
	}

	public List<Item> searchItemByImei(String imei) {
		List<Item> temp = new ArrayList<>();
		for (ItemDetail id : Data.listItemDetail) {
			if (id.getImei().equals(imei)) {
				Item i = Data.getItem(id.getItemId());
				temp.add(i);
			}
		}
		return temp;
	}

	public boolean containsOf(String s1, String s2) {
		boolean isContain = false;
		StringTokenizer st = new StringTokenizer(s2, " ");
		while (st.hasMoreElements()) {
			String nextToken = st.nextToken();
			if (s1.contains(nextToken)) {
				s1 = s1.replaceFirst(nextToken, "");
				isContain = true;

			} else {
				isContain = false;
				return false;
			}
		}

		return isContain;
	}

	public void replace(String s, String s1, String s2) {
		s.replace(s1, s2);
	}

}
