package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import model.Item;

public class ItemDAO {
	private static HibernateUtils utils = new HibernateUtils();

	public static Item getItem(int id) {
		try {
			Session session = utils.getSession();
			session.beginTransaction();
			Item item = (Item) session.get(Item.class, id);
			session.beginTransaction().commit();
			return item;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Item getItemByName(String name) {
		List<Item> temp = getItem("From Item where name = '" + name + "'");
		if (temp.isEmpty()) {
			return null;
		} else {
			return temp.get(0);
		}
	}

	public static List<Item> getItemes() {
		try {
			Session session = utils.getSession();
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Item> list = session.createQuery("FROM Item").list();
			session.beginTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<Item> getItem(String query) {
		try {
			Session session = utils.getSession();
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Item> list = session.createQuery(query).list();
			session.beginTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static int getNextId() {
		int id = -1;
		if (getItemes() != null) {
			id = getItemes().get(getItemes().size() - 1).getItemId() + 1;
		}
		return id;
	}

	public static int getId(String name) {
		List<Item> list = getItem("From Item where name = '" + name + "'");
		if (list.isEmpty()) {
			return -1;
		}
		return list.get(0).getItemId();
	}

	public static void insert(Item Item) {
		process(Item, "insert");
	}

	public static void update(Item Item) {
		process(Item, "update");
	}

	public static void delete(Item Item) {
		process(Item, "delete");
	}

	private static void process(Item Item, String mode) {
		try {
			Session session = utils.getSession();
			session.beginTransaction();

			switch (mode) {
			case "insert":
				session.save(Item);
				break;
			case "update":
				session.update(Item);
				break;
			case "delete":
				session.delete(Item);
				break;
			}

			session.beginTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<String> getItemName() {
		try {
			Session session = utils.getSession();
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Item> list = session.createQuery("FROM Item").list();
			session.beginTransaction().commit();
			List<String> listName = new ArrayList<String>();
			if (!list.isEmpty()) {
				for (Item i : list) {
					listName.add(i.getName());
				}
			}
			return listName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
