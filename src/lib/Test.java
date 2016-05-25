package lib;

import dao.ItemDAO;

public class Test {
	public static void main(String[] args) {
		int size = ItemDAO.getItemes().size() - 1;
		int id = ItemDAO.getItemes().get(size).getItemId() + 1;
		System.out.println(id);
	}

}
