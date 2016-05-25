package dao;

import java.util.List;

import org.hibernate.Session;

import model.BillDetail;

public class BillDetailDAO {
	private static HibernateUtils utils = new HibernateUtils();

	public static BillDetail getSaleBillDetail(int id) {
		try {
			Session session = utils.getSession();
			session.beginTransaction();
			BillDetail saleBillDetail = (BillDetail) session.get(BillDetail.class, id);
			session.beginTransaction().commit();
			return saleBillDetail;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<BillDetail> getSaleBillDetails() {
		try {
			Session session = utils.getSession();
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<BillDetail> list = session.createQuery("FROM SaleBillDetail").list();
			session.beginTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<BillDetail> getBillDetail(String query) {
		System.out.println(query);
		try {
			Session session = utils.getSession();
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<BillDetail> list = session.createQuery(query).list();
			session.beginTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void insert(BillDetail SaleBillDetail) {
		process(SaleBillDetail, "insert");
	}

	public static void update(BillDetail SaleBillDetail) {
		process(SaleBillDetail, "update");
	}

	public static void delete(BillDetail SaleBillDetail) {
		process(SaleBillDetail, "delete");
	}

	private static void process(BillDetail SaleBillDetail, String mode) {
		try {
			Session session = utils.getSession();
			session.beginTransaction();

			switch (mode) {
			case "insert":
				session.save(SaleBillDetail);
				break;
			case "update":
				session.update(SaleBillDetail);
				break;
			case "delete":
				session.delete(SaleBillDetail);
				break;
			}

			session.beginTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
