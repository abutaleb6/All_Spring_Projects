package com.ibcs.acl.db;

/**
 *
 * @author Ahasanul Ashid, IBCS
 * @author Abu Taleb, IBCS
 */
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
/**
*
* @author Ahasanul Ashid, IBCS
* @author Abu Taleb, IBCS
* 
*/
public class HibernateUtil {
	
	@Autowired
	private static final SessionFactory sessionFactory;

	static {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			sessionFactory = new Configuration().configure()
					.buildSessionFactory();
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
