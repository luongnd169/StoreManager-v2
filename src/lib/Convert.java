package lib;

import java.text.NumberFormat;
import java.text.ParseException;
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

	public static String formatDateTime(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy '&' hh:mm:ss");
		return dateFormat.format(date);
	}

	public static String formatDateSQL(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
	}

	public static Date formatDate(Date date, String type) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(type);
		try {
			return dateFormat.parse(dateFormat.format(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
	public static String getDate(String date, boolean increase) {
		StringTokenizer st = new StringTokenizer(date, " ,-");
		int index = st.countTokens();
		int day = 0;
		int month = 0;
		int year = 0;
		if (index == 2) {
			day = Integer.parseInt(st.nextToken());
			month = Integer.parseInt(st.nextToken());
			year = new Date().getYear() + 1900;
		} else if (index == 3) {
			day = Integer.parseInt(st.nextToken());
			month = Integer.parseInt(st.nextToken());
			year = Integer.parseInt(st.nextToken());
		} else {
			return "";
		}
		if (increase) {
			day++;
		} 
		String formatedDate = year + "-" + month + "-" + day;
		return formatedDate;
	}

}
