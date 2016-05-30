package dao;

import java.util.List;

import org.hibernate.Session;

import model.ItemDetail;

public class ItemDetailDAO {
	private static HibernateUtils utils = new HibernateUtils();

	public static ItemDetail getItemDetail(int id) {
		try {
			Session session = utils.getSession();
			session.beginTransaction();
			ItemDetail ItemDetail = (ItemDetail) session.get(ItemDetail.class, id);
			session.beginTransaction().commit();
			return ItemDetail;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<ItemDetail> getItemDetails() {
		try {
			Session session = utils.getSession();
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<ItemDetail> list = session.createQuery("FROM ItemDetail").list();
			session.beginTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<ItemDetail> getItemDetail(String query) {
		try {
			Session session = utils.getSession();
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<ItemDetail> list = session.createQuery(query).list();
			session.beginTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void insert(ItemDetail ItemDetail) {
		process(ItemDetail, "insert");
	}

	public static void update(ItemDetail ItemDetail) {
		process(ItemDetail, "update");
	}

	public static void delete(ItemDetail ItemDetail) {
		process(ItemDetail, "delete");
	}

	private static void process(ItemDetail ItemDetail, String mode) {
		try {
			Session session = utils.getSession();
			session.beginTransaction();

			switch (mode) {
			case "insert":
				session.save(ItemDetail);
				break;
			case "update":
				session.update(ItemDetail);
				break;
			case "delete":
				session.delete(ItemDetail);
				break;
			}

			session.beginTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
