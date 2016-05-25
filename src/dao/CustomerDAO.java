package dao;

import java.util.List;

import org.hibernate.Session;

import model.Customer;

public class CustomerDAO {

	private static HibernateUtils utils = new HibernateUtils();

	public static Customer getCustomer(int id) {
		try {
			Session session = utils.getSession();
			session.beginTransaction();
			Customer customer = (Customer) session.get(Customer.class, id);
			session.beginTransaction().commit();
			return customer;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<Customer> getCustomers() {
		try {
			Session session = utils.getSession();
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Customer> list = session.createQuery("FROM Customer").list();
			session.beginTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<Customer> getCustomer(String query) {
		System.out.println(query);
		try {
			Session session = utils.getSession();
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Customer> list = session.createQuery(query).list();
			session.beginTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Integer getId(String name, String type) {
		String query = "";
		switch (type) {
		case "customer":
			query = "From Customer where name = '" + name + "' and provider = 0";
			break;

		case "provider":
			query = "From Customer where name = '" + name + "' and provider = 1";
			break;
		}
		List<Customer> list = getCustomer(query);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0).getCustomerId();
	}

	public static Customer getCustomerByPhone(String phone) {
		List<Customer> list = getCustomer("From Customer where phone = '" + phone + "'");
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public static void insert(Customer customer) {
		process(customer, "insert");
	}

	public static void update(Customer customer) {
		process(customer, "update");
	}

	public static void delete(Customer customer) {
		process(customer, "delete");
	}

	private static void process(Customer customer, String mode) {
		try {
			Session session = utils.getSession();
			session.beginTransaction();

			switch (mode) {
			case "insert":
				session.save(customer);
				break;
			case "update":
				session.update(customer);
				break;
			case "delete":
				session.delete(customer);
				break;
			}

			session.beginTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
