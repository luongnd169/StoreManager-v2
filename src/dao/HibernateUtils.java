package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import lib.IPathStorage;

public class HibernateUtils {

	public HibernateUtils() {

	}

	public SessionFactory getSessionFactory() {
		try {
			AnnotationConfiguration config = new AnnotationConfiguration();
			config.configure(IPathStorage.HIBERNATE_CONFIG);
			return config.buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Session getSession() {
		try {
			return getSessionFactory().getCurrentSession();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
