package dao;

import java.util.List;

import org.hibernate.Session;

import model.Fee;

public class FeeDAO {

	private static HibernateUtils utils = new HibernateUtils();

	public static Fee getFee(int id) {
		try {
			Session session = utils.getSession();
			session.beginTransaction();
			Fee fee = (Fee) session.get(Fee.class, id);
			session.beginTransaction().commit();
			return fee;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<Fee> getFees() {
		try {
			Session session = utils.getSession();
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Fee> list = session.createQuery("FROM Fee").list();
			session.beginTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<Fee> getFee(String query) {
		try {
			Session session = utils.getSession();
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Fee> list = session.createQuery(query).list();
			session.beginTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<Fee> getByDate(String from, String to) {
		try {
			Session session = utils.getSession();
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Fee> list = session.createQuery("From Fee Where date Between '" + from + "' and '" + to + "'").list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void insert(Fee fee) {
		process(fee, "insert");
	}

	public static void update(Fee fee) {
		process(fee, "update");
	}

	public static void delete(Fee fee) {
		process(fee, "delete");
	}

	private static void process(Fee fee, String mode) {
		try {
			Session session = utils.getSession();
			session.beginTransaction();

			switch (mode) {
			case "insert":
				session.save(fee);
				break;
			case "update":
				session.update(fee);
				break;
			case "delete":
				session.delete(fee);
				break;
			}

			session.beginTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
