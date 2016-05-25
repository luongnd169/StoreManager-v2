package lib;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import model.Fee;
import model.Item;

public class Convert {

	public static String numberToString(String s) {
		return NumberFormat.getNumberInstance(Locale.US).format(Integer.parseInt(s));
	}

	public static String stringToNumber(String number) {
		String s = "";
		StringTokenizer st = new StringTokenizer(number, ",");
		while (st.hasMoreTokens()) {
			s += st.nextToken();
		}
		return s;
	}

	public static String formatDate(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		return dateFormat.format(date);
	}

	public static String formatDateSQL(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
	}

	public static void main(String[] args) {
		System.out.println(numberToString(""));
	}

	public static List<Item> convertListItem(List<Item> list) {
		List<Item> temp = new ArrayList<>();
		for (Item i : list) {
			temp.add(i);
		}
		for (Item i : temp) {
			i.setPrice(numberToString(i.getPrice()));
		}
		return temp;
	}

	public static List<Item> returnListItem(List<Item> list) {
		List<Item> temp = new ArrayList<>();
		for (Item i : list) {
			temp.add(i);
		}
		for (Item i : temp) {
			i.setPrice(stringToNumber(i.getPrice()));
		}

		return temp;
	}

	public static List<Fee> convertListFee(List<Fee> list) {
		for (Fee e : list) {
			e.setValue(numberToString(e.getValue()));
		}
		return list;
	}

	public static List<Fee> returnListFee(List<Fee> list) {
		List<Fee> temp = new ArrayList<>();
		for (Fee e : list) {
			temp.add(e);
		}
		for (Fee e : temp) {
			e.setValue(stringToNumber(e.getValue()));
		}
		return temp;
	}

	@SuppressWarnings("deprecation")
	public static String getDate(String date) {
		StringTokenizer st = new StringTokenizer(date, " ,-");
		int index = st.countTokens();

		String day = "";
		String month = "";
		String year = "";
		if (index == 2) {
			day = st.nextToken();
			month = st.nextToken();
			year = new Date().getYear() + 1900 + "";
		} else if (index == 3) {
			day = st.nextToken();
			month = st.nextToken();
			year = st.nextToken();
		}
		String formatedDate = year + "-" + month + "-" + day;
		return formatedDate;
	}

}
