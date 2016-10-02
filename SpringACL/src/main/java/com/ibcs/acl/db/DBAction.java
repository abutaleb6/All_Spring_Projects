package com.ibcs.acl.db;

/**
 *
 * @author Ahasanul Ashid, IBCS
 * @author Abu Taleb, IBCS
 */

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibcs.acl.model.AclUIHolder;
import com.ibcs.acl.model.AuthUser;
import com.ibcs.acl.model.Menu;
import com.ibcs.acl.model.ObjectReference;
import com.ibcs.acl.model.PermissionTable;
import com.ibcs.acl.model.Roles;
import com.ibcs.acl.model.User;

/**
 *
 * @author Ahasanul Ashid, IBCS
 * @author Abu Taleb, IBCS
 * 
 */

public class DBAction {

	private Object ob;

	 private Session session;
	 //session=HibernateUtil.getSessionFactory().getCurrentSession();
	public DBAction() {
		this.session=HibernateUtil.getSessionFactory().getCurrentSession();
	}

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void add(Object o) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		session.save(o);

		session.getTransaction().commit();
		// session.close();

		// init();
	}

	/*
	 * // public List GetCountryName(String table) { // // Session session =
	 * HibernateUtil.getSessionFactory().getCurrentSession(); //
	 * session.beginTransaction(); // // List area = // session.createSQLQuery(
	 * "select COUNTRY_NAME from IFWOutRate GROUP BY COUNTRY_NAME ORDER BY COUNTRY_NAME ASC"
	 * ).list(); // // // session.getTransaction().commit(); // // return area;
	 * // }
	 * 
	 * public List<String> getTrunk() { Session session =
	 * HibernateUtil.getSessionFactory().getCurrentSession();
	 * session.beginTransaction(); Query query =
	 * session.createSQLQuery("select TRUNK from IFW_TRUNK"); List<String> l =
	 * query.list(); return l; }
	 * 
	 * public List<IbcsOperatorList> getANS() { Session session =
	 * HibernateUtil.getSessionFactory().getCurrentSession();
	 * session.beginTransaction(); Criteria criteria =
	 * session.createCriteria(IbcsOperatorList.class);
	 * criteria.add(Restrictions.eq("operator_type", "ANS"));
	 * List<IbcsOperatorList> l = criteria.list(); System.out.println(l.size());
	 * session.close(); return l; }
	 * 
	 * public List<IbcsOperatorList> getIGW() { Session session =
	 * HibernateUtil.getSessionFactory().getCurrentSession();
	 * session.beginTransaction(); Criteria criteria =
	 * session.createCriteria(IbcsOperatorList.class);
	 * criteria.add(Restrictions.eq("operator_type", "IGW"));
	 * List<IbcsOperatorList> l = criteria.list(); System.out.println("");
	 * session.close(); return l; }
	 */
	public Boolean checkIfExists(Class cls, String field, String val) {
		Boolean bval = true;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(cls);
		criteria.add(Restrictions.eq(field, val));
		List<Object> l = criteria.list();
		if (l.size() < 1) {
			bval = false;
		} else
			bval = true;
		return bval;
	}

	public List<Object> NongetAdminMenu() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Menu.class);
		criteria.add(Restrictions.eq("is_admin", "0"));
		List<Object> l = criteria.list();
		session.close();
		return l;
	}

	/*
	 * public List GetCountryName() {
	 * 
	 * Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	 * session.beginTransaction();
	 * 
	 * List area = session .createSQLQuery(
	 * "select COUNTRY_NAME from IFW_OUT_RATE GROUP BY COUNTRY_NAME ORDER BY COUNTRY_NAME ASC"
	 * ) .list();
	 * 
	 * // session.getTransaction().commit();
	 * 
	 * return area; }
	 */

	/*
	 * public List GettrunkcodeName() {
	 * 
	 * Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	 * session.beginTransaction();
	 * 
	 * List area = session.createSQLQuery(
	 * "select distinct code from IFW_TRUNK  ORDER BY code ASC") .list();
	 * 
	 * // session.getTransaction().commit();
	 * 
	 * return area; }
	 */

	public int login(String user, String pass) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Query query = session.createQuery("from Acount a where a.userid ='"
				+ user + "'");
		List users = query.list();
		int i = users.size();

		session.close();

		// session.getTransaction().commit();

		return i;
	}

	public Object getById(int id, Class cls) throws ClassNotFoundException {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Object o = (Object) session.get(cls, id);
		// session.close();
		return o;
	}

	public List<Object> getAllTest(String cls) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Query query = session.createQuery("from " + cls + " a");
		List<Object> l = query.list();
		return l;

	}

	/*
	 * // IFWOutRate
	 * 
	 * public List<IFWOutRate> getIFWOutRate(String country) { Session session =
	 * HibernateUtil.getSessionFactory().getCurrentSession();
	 * session.beginTransaction(); Criteria criteria =
	 * session.createCriteria(IFWOutRate.class);
	 * criteria.add(Restrictions.eq("country_name", country)); List<IFWOutRate>
	 * l = criteria.list(); session.close(); return l; }
	 * 
	 * public List<IfwTrunk> getIFWTrunk(String country) { Session session =
	 * HibernateUtil.getSessionFactory().getCurrentSession();
	 * session.beginTransaction(); Criteria criteria =
	 * session.createCriteria(IfwTrunk.class);
	 * criteria.add(Restrictions.eq("code", country)); List<IfwTrunk> l =
	 * criteria.list(); session.close(); return l; }
	 */
	public List<Object> getAll(Class cls) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(cls).addOrder(
				Order.desc("id"));
		criteria.setFetchSize(100);
		// session.getTransaction().commit();
		List<Object> l = criteria.list();

		session.close();
		return l;
	}

	public void delete(int id, Class cls) throws ClassNotFoundException {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Object s = getById(id, cls);

		session.delete(s);
		session.getTransaction().commit();
		// session.clear();
		// session.close();
	}

	public void update(Object o) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		session.merge(o);
		session.getTransaction().commit();
		// session.close();
	}

	public List<Object> GeneralSql(String sql_query)

	{

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Query query = session.createSQLQuery(sql_query);

		List<Object> l = query.list();
		session.close();
		return l;

	}

	/*
	 * public List<Object> GetFormula(String sid) { Session session =
	 * HibernateUtil.getSessionFactory().getCurrentSession();
	 * session.beginTransaction(); Criteria criteria =
	 * session.createCriteria(FormulaSet.class);
	 * criteria.add(Restrictions.eq("sourceId", sid)); List<Object> l =
	 * criteria.list(); session.close(); return l; }
	 * 
	 * public List<Object> GetMonWiseDetail(String sn) { Session session =
	 * HibernateUtil.getSessionFactory().getCurrentSession();
	 * session.beginTransaction(); Criteria criteria =
	 * session.createCriteria(MonWiseDetail.class);
	 * criteria.add(Restrictions.eq("source_network", sn)); List<Object> l =
	 * criteria.list(); session.close(); return l; }
	 * 
	 * public List<Object> GetMonWiseDetailIntIn(String sn) { Session session =
	 * HibernateUtil.getSessionFactory().getCurrentSession();
	 * session.beginTransaction(); Criteria criteria =
	 * session.createCriteria(MonWiseDetailIntIn.class);
	 * criteria.add(Restrictions.eq("source_network", sn)); List<Object> l =
	 * criteria.list(); session.close(); return l; }
	 */

	/*
	 * public List<Object> GetMonWiseDetailIntOut(String sn) { Session session =
	 * HibernateUtil.getSessionFactory().getCurrentSession();
	 * session.beginTransaction(); Criteria criteria =
	 * session.createCriteria(MonWiseDetailIntOut.class);
	 * criteria.add(Restrictions.eq("source_network", sn)); List<Object> l =
	 * criteria.list(); session.close(); return l; }
	 */

	/*
	 * public List<Object> Getvalidation(String sid) { Session session =
	 * HibernateUtil.getSessionFactory().getCurrentSession();
	 * session.beginTransaction(); Criteria criteria =
	 * session.createCriteria(ValidationSet.class);
	 * criteria.add(Restrictions.eq("source_id", sid)); List<Object> l =
	 * criteria.list(); session.close(); return l; }
	 */

	public List<Object> search(String id, Class cls) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(cls);
		criteria.add(Restrictions.eq("sourceId", id));
		List<Object> l = criteria.list();
		session.close();
		return l;
	}

	/*
	 * public List<ReportTable> getReportByType(String reportType) { Session
	 * session = HibernateUtil.getSessionFactory().getCurrentSession();
	 * session.beginTransaction(); Criteria criteria =
	 * session.createCriteria(ReportTable.class);
	 * criteria.add(Restrictions.eq("report_type", reportType))
	 * .add(Restrictions.eq("parameter_flag", 0)) .addOrder(Order.asc("id"));
	 * List<ReportTable> l = criteria.list(); session.close(); return l; }
	 */

	/*
	 * public List<GetcoExternalSuspence> getData(String st, String en) {
	 * Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	 * session.beginTransaction(); Criteria criteria =
	 * session.createCriteria(GetcoExternalSuspence.class);
	 * criteria.add(Restrictions.between("ans_time", st, en)).add(
	 * (Restrictions.eq("status", 0))); List<GetcoExternalSuspence> l =
	 * criteria.list(); session.close(); return l; }
	 */

	/*
	 * public List<IbcsDomesticDialDigit> GetByPrefix(String opcode) { Session
	 * session = HibernateUtil.getSessionFactory().getCurrentSession();
	 * session.beginTransaction(); Criteria criteria =
	 * session.createCriteria(IbcsDomesticDialDigit.class);
	 * criteria.add(Restrictions.eq("operator_code", opcode));
	 * List<IbcsDomesticDialDigit> l = criteria.list(); session.close(); return
	 * l; }
	 */
	/*
	 * public List<SettlementDetail> GetByDetails(String partner_id, String
	 * call_type) { Session session =
	 * HibernateUtil.getSessionFactory().getCurrentSession();
	 * session.beginTransaction(); Criteria criteria =
	 * session.createCriteria(SettlementDetail.class);
	 * criteria.add(Restrictions.eq("id_fk", Integer.parseInt(partner_id)));
	 * List<SettlementDetail> l = criteria.list(); session.close(); return l; }
	 */
	public List<Object> Login(String id, String pass) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("userid", id)).add(
				Restrictions.eq("pass", pass));
		List<Object> l = criteria.list();
		session.close();

		return l;

	}

	public List<Roles> getRoleId(String role) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Roles.class);
		criteria.add(Restrictions.eq("role", role));
		List<Roles> l = criteria.list();
		session.close();
		return l;
	}
	
	public int getRoleIdByName(String rolename) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Roles.class);
		criteria.add(Restrictions.eq("role", rolename));
		List<Roles> l = criteria.list();
		int roleId = l.get(0).getId();
		session.close();
		return roleId;
	}

	public List<ObjectReference> getObjectId(String object) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(ObjectReference.class);
		criteria.add(Restrictions.eq("class_name", object));
		List<ObjectReference> l = criteria.list();
		session.close();
		return l;
	}

	public List<Menu> getChildMenu(String parent) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Menu.class);
		criteria.add(Restrictions.eq("parent_menu", parent));
		List<Menu> l = criteria.list();
		session.close();
		return l;
	}

	public List<PermissionTable> getPermission(int roleId, int objId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Query query = session
				.createQuery("from PermissionTable a where a.role_id ="
						+ roleId + "AND a.object_id =" + objId);

		// System.out.println("eror-->"+query.toString());

		List<PermissionTable> l = query.list();

		session.close();
		return l;
	}
	
	public List<PermissionTable> getPermissionByRoleName(String roleName) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		//int roleId = this.getRoleIdByName(roleName);

		//Query query = session
		//		.createQuery("select p_read, p_write, p_delete, p_edit from permission_table a where a.role_id = (select role_id from roles where role="+roleName+")");
		Query query = session
				.createSQLQuery("select getObject(OBJECT_ID),getRole(ROLE_ID),P_READ,P_WRITE,P_EDIT,P_DELETE  from permission_table where role_id=(select role_id from roles where role = '"+roleName+ "')");

		// System.out.println("eror-->"+query.toString());

		List<PermissionTable> l = query.list();

		session.close();
		return l;
	}

	/*
	 * public String calSuspence(String s1, String s2) { String msg = "OK";
	 * Configuration c = new Configuration().configure(); SessionFactory sf =
	 * c.buildSessionFactory(); Session session = sf.openSession();
	 * session.beginTransaction(); try { Query q = session
	 * .createSQLQuery(" { call POPULATE_RERATE_DATA (?,?,?,?) }");
	 * 
	 * q.setString(0, s1); // first parameter index start with 1 q.setString(1,
	 * s2); q.setString(2, ""); q.setString(3, ""); // second parameter
	 * q.executeUpdate(); session.getTransaction().commit();
	 * 
	 * }
	 * 
	 * catch (Exception e) { msg = e + " ";
	 * 
	 * }
	 * 
	 * session.close(); sf.close(); return msg;
	 * 
	 * }
	 * 
	 * public String callTrunkRefresh() { String msg = "OK"; Configuration c =
	 * new Configuration().configure(); SessionFactory sf =
	 * c.buildSessionFactory(); Session session = sf.openSession();
	 * session.beginTransaction(); try { Query q = session
	 * .createSQLQuery(" { call Getco_Etl_Proc_Func_Refresh }");
	 * 
	 * // second parameter q.executeUpdate(); session.getTransaction().commit();
	 * 
	 * }
	 * 
	 * catch (Exception e) { msg = e + " ";
	 * 
	 * }
	 * 
	 * session.close(); sf.close(); return msg;
	 * 
	 * }
	 */
	public List<AuthUser> searchUser(String id) {
		// int idd=Integer.parseInt(id);
		Session session =HibernateUtil.getSessionFactory().getCurrentSession();
		//Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(AuthUser.class);
		criteria.add(Restrictions.eq("userid", id));
		List<AuthUser> l = criteria.list();
		session.close();
		return l;
	}

	public List test() {

		Session session =
		HibernateUtil.getSessionFactory().getCurrentSession();
		//Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(AclUIHolder.class);

		Query query = session
				.createSQLQuery("select getObject(OBJECT_ID),getRole(ROLE_ID),P_READ,P_WRITE,P_EDIT,P_DELETE  from permission_table");

		List<Object> l = query.list();
		System.out.println(l.get(1).toString().toString());
		// AclUIHolder a=(AclUIHolder) l.get(1);
		session.close();
		return l;

	}

	public String RoleName(int id) {

		Session session =
		 HibernateUtil.getSessionFactory().getCurrentSession();
		//Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(AclUIHolder.class);

		Query query = session.createSQLQuery("select getRole(" + id
				+ ") from dual");

		List<String> l = query.list();
		session.close();
		return l.toString();

	}

	public String ObjectName(int id) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Query query = session.createSQLQuery("select getObject(" + id
				+ ") from dual");

		List<String> l = query.list();
		session.close();
		return l.toString();

	}

	/*
	 * public List<ReportTable> GetReport(String reportName) { int i = 0;
	 * Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	 * session.beginTransaction(); Criteria criteria =
	 * session.createCriteria(ReportTable.class);
	 * criteria.add(Restrictions.eq("report_name", reportName)).add(
	 * Restrictions.eq("parameter_flag", i)); List<ReportTable> l =
	 * criteria.list(); session.close(); return l; }
	 * 
	 * public List<Object> getArea(String country, Class cls) {
	 * 
	 * Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	 * session.beginTransaction(); Criteria criteria =
	 * session.createCriteria(cls); criteria.add(Restrictions.eq("country_name",
	 * country)); List<Object> l = criteria.list(); session.close(); return l; }
	 * 
	 * public List<Object> btrcGetById(int id, Class cls) {
	 * 
	 * Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	 * session.beginTransaction(); Criteria criteria =
	 * session.createCriteria(cls); criteria.add(Restrictions.eq("id", id));
	 * List<Object> l = criteria.list(); session.close(); return l; }
	 * 
	 * public void calSummaryProcedure(String dateStr, String decission, String
	 * vat) {
	 * 
	 * Configuration c = new Configuration().configure(); SessionFactory sf =
	 * c.buildSessionFactory(); Session session = sf.openSession();
	 * session.beginTransaction(); try { dateStr = dateStr.substring(0,
	 * dateStr.length() - 3); Query q = session
	 * .createSQLQuery(" { call  retrieve_summary_data(?,?,?) }");
	 * 
	 * q.setString(0, dateStr); // first parameter index start with 1
	 * q.setString(1, vat); q.setString(2, decission); // second parameter
	 * q.executeUpdate();
	 * 
	 * session.getTransaction().commit(); session.close(); sf.close(); }
	 * 
	 * catch (Exception e) { System.out.println("Error is ------------------>>>"
	 * + e); } }
	 * 
	 * public List<MonthlyReceivableMaster> getMonthlyReceivableMaster(Date
	 * date, String decission, String vat) throws ParseException { int i = 0;
	 * System.out.println("date vat" + date.toString() + " " + vat); Session
	 * session = HibernateUtil.getSessionFactory().getCurrentSession();
	 * session.beginTransaction(); Criteria criteria = session
	 * .createCriteria(MonthlyReceivableMaster.class);
	 * criteria.add(Restrictions.eq("month_year", date));
	 * 
	 * List<MonthlyReceivableMaster> l = criteria.list();
	 * 
	 * session.close(); return l; }
	 * 
	 * public void deleteSummaryProcedure(String dateStr) { try { Configuration
	 * c = new Configuration().configure(); SessionFactory sf =
	 * c.buildSessionFactory(); Session session = sf.openSession();
	 * session.beginTransaction(); dateStr = dateStr.substring(0,
	 * dateStr.length() - 3); Query q = session
	 * .createSQLQuery(" { call delete1_summary_data(?) }");
	 * 
	 * q.setString(0, dateStr.toString()); // first parameter index start //
	 * with 1 // second parameter q.executeUpdate();
	 * session.getTransaction().commit(); session.close(); sf.close(); } catch
	 * (Exception ex) { System.out.println("ex---------------- " + ex); } }
	 * 
	 * public String RateReRate(String from, String to, String call_type, String
	 * op_type) { String s = ""; try { Configuration c = new
	 * Configuration().configure(); SessionFactory sf = c.buildSessionFactory();
	 * Session session = sf.openSession(); session.beginTransaction(); //
	 * dateStr = dateStr.substring(0, dateStr.length()-3); Query q = session
	 * .createSQLQuery(" { call populate_rerate_data(?,?,?,?) }");
	 * 
	 * q.setString(0, from); q.setString(1, to); q.setString(2, call_type);
	 * q.setString(3, op_type); // first parameter index start with 1 // second
	 * parameter q.executeUpdate(); session.getTransaction().commit();
	 * session.close(); sf.close(); s = "Successfull"; } catch (Exception ex) {
	 * System.out.println("ex---------------- " + ex); s = ex + " "; } return s;
	 * }
	 * 
	 * public String call_populate_rerate_suspense_data() { String msg = "OK";
	 * Configuration c = new Configuration().configure(); SessionFactory sf =
	 * c.buildSessionFactory(); Session session = sf.openSession();
	 * session.beginTransaction(); try { Query q = session
	 * .createSQLQuery(" { call POPULATE_RERATE_SUSPENCE_DATA }");
	 * 
	 * // second parameter q.executeUpdate(); session.getTransaction().commit();
	 * 
	 * }
	 * 
	 * catch (Exception e) { msg = e + " ";
	 * 
	 * }
	 * 
	 * session.close(); sf.close(); return msg;
	 * 
	 * }
	 * 
	 * public String call_populate_suspense_data() { String msg = "OK";
	 * Configuration c = new Configuration().configure(); SessionFactory sf =
	 * c.buildSessionFactory(); Session session = sf.openSession();
	 * session.beginTransaction(); try { Query q = session
	 * .createSQLQuery(" { call POPULATE_SUSPENCE_DATA }");
	 * 
	 * // second parameter q.executeUpdate(); session.getTransaction().commit();
	 * 
	 * }
	 * 
	 * catch (Exception e) { msg = e + " ";
	 * 
	 * }
	 * 
	 * session.close(); sf.close(); return msg;
	 * 
	 * }
	 * 
	 * public String AddIFWOutRate(IFWOutRate ifw) { String s = ""; try {
	 * Configuration c = new Configuration().configure(); SessionFactory sf =
	 * c.buildSessionFactory(); Session session = sf.openSession();
	 * session.beginTransaction(); // dateStr = dateStr.substring(0,
	 * dateStr.length()-3);
	 * 
	 * Query q = session
	 * .createSQLQuery(" { call INSERT_OUT_DATA(?,?,?,?,?,?,?,?,?,?) }");
	 * 
	 * q.setString(0, ifw.getArea_code()); q.setString(1, ifw.getName());
	 * q.setString(2, ifw.getImpact_cat()); q.setDouble(3, ifw.getX_peak());
	 * q.setDouble(4, ifw.getOff_peak_x()); q.setDouble(5, ifw.getY_val());
	 * q.setDouble(6, ifw.getEisd_xrate()); q.setString(7,
	 * ifw.getCountry_name()); q.setDate(8,
	 * Date.valueOf(ifw.getActive_date().toString())); if
	 * (ifw.getActive_date().equals("") || ifw.getDummy_date().length() <= 0) {
	 * 
	 * q.setString(9, ifw.getDummy_date()); }
	 * 
	 * else q.setDate(9, java.sql.Date.valueOf(ifw.getDummy_date()));
	 * 
	 * // first parameter index start with 1 // second parameter
	 * q.executeUpdate(); session.getTransaction().commit(); session.close();
	 * sf.close(); s = "Successfull"; } catch (Exception ex) {
	 * System.out.println("ex---------------- " + ex); s = ex + " "; } return s;
	 * }
	 */
	public String RunSuspenseProcedure1(String from, String to, int p_call,
			String p_op) {
		String s = "";

		try {
			Configuration c = new Configuration().configure();
			SessionFactory sf = c.buildSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();
			// dateStr = dateStr.substring(0, dateStr.length()-3);
			Query q = session
					.createSQLQuery(" { call POPULATE_SUSPENCE_DATA(?,?,?,?) }");

			q.setString(0, from);
			q.setString(1, to);
			q.setInteger(2, p_call);
			q.setString(3, ",");

			// first parameter index start with 1
			// second parameter
			q.executeUpdate();
			session.getTransaction().commit();
			session.close();
			sf.close();
			s = "Successfull";
		} catch (Exception ex) {
			System.out.println("ex---------------- " + ex);
			s = ex + " ";
		}
		return s;
	}

	public String getco_etl_temp_proc(String pmnyr, String tspace) {
		String s = "";

		try {
			Configuration c = new Configuration().configure();
			SessionFactory sf = c.buildSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();
			// dateStr = dateStr.substring(0, dateStr.length()-3);
			Query q = session
					.createSQLQuery(" { call getco_etl_icx_temp_table(?,?,?) }");
			// '200912','DEC_09_ALL_SPACE'
			q.setString(0, pmnyr);
			q.setString(1, "1");
			q.setString(2, tspace);

			// first parameter index start with 1
			// second parameter
			q.executeUpdate();
			session.getTransaction().commit();
			session.close();
			sf.close();
			s = "Successfull";
		} catch (Exception ex) {
			System.out.println("ex---------------- " + ex);
			s = ex + " ";
		}
		return s;
	}

	public List GetTotalSum(int i) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		List area = session.createSQLQuery(
				"select sum(cheque_amt) from settlement_detail where ID_FK="
						+ i).list();

		// session.getTransaction().commit();

		return area;
	}

	public List GetTotalSuspense() {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		List area = session
				.createSQLQuery(
						"select count(*) from GETCO_EXTERNAL_SUSPENCE where T_ALERT='TF' and O_ALERT='OF' and R_ALERT='RF'")
				.list();

		// session.getTransaction().commit();
		session.close();
		return area;
	}

	public List GetTableSpace() {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		List area = session
				.createSQLQuery(
						"SELECT  tablespace_name FROM dba_free_space where tablespace_name not in ('SYSAUX','UNDOTBS1','USERS','SYSTEM','EXAMPLE') GROUP BY tablespace_name")
				.list();

		// session.getTransaction().commit();

		return area;
	}

	public List<AuthUser> EditUser(String uname) {
		int i = 0;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(AuthUser.class);
		criteria.add(Restrictions.eq("userid", uname));
		List<AuthUser> l = criteria.list();
		session.close();
		return l;
	}
}
