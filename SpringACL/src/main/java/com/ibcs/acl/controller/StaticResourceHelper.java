package com.ibcs.acl.controller;

/**
 *
 * @author Ahasanul Ashid, IBCS
 * @author Abu Taleb, IBCS
 */

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibcs.acl.db.AclManipulation;
import com.ibcs.acl.model.ACL;
import com.ibcs.acl.model.AclUIHolder;
import com.ibcs.acl.model.ClassUtility;
import com.ibcs.acl.model.Menu;
import com.ibcs.acl.model.MenuItemParentHolder;
import com.ibcs.acl.security.Permission;
import com.ibcs.acl.service.DBCon;
/**
*
* @author Ahasanul Ashid, IBCS
* @author Abu Taleb, IBCS
*/
public class StaticResourceHelper {

	private static Connection con;

	private static AclManipulation ac = null;
	private static MenuItemParentHolder mx = null;
	private static List<AclUIHolder> acl = null;
	private static ACL acx = null;

	public static Map<String, Permission> getLoadPermission() {
		return StaticResourceHelper.getAc().LoadPermission();
	}

	/*public static HashMap<String, ClassUtility> getLclass() {
		return StaticResourceHelper.getMx().getLclass();
	}

	public static Map<String, List<Menu>> getMenuMap() {
		return StaticResourceHelper.getMx().getMenuMap();
	}*/

	public static List<String> getMenuSerial() {
		return StaticResourceHelper.getAcx().returnMenu();
	}

	/*public static HashMap<String, String> getRoot() {
		return StaticResourceHelper.getMx().getRootMenus();
	}*/

	public static AclManipulation getAc() {
		if (StaticResourceHelper.ac == null) {
			StaticResourceHelper.ac = new AclManipulation();
			// System.out.println(ac.toString()+"First Time Caleed and its null");

		}
		// System.out.print(" Called and its not null");

		return ac;

	}

	/*public static MenuItemParentHolder getMx() {
		if (mx == null) {
			mx = StaticResourceHelper.getAc().MenuGeneration();
		}

		return mx;
	}*/

	public static List<AclUIHolder> getAcl() {
		StaticResourceHelper.acl = StaticResourceHelper.ac.getAclUI();
		return StaticResourceHelper.acl;
	}

	public static ACL getAcx() {
		if (StaticResourceHelper.acx == null) {

			StaticResourceHelper.acx = new ACL();
		}

		return acx;
	}

	public static Connection getCon() {

		if (con == null) {

			DBCon db = new DBCon();

			try {
				con = db.getJDBCConnection();
			}

			catch (Exception e) {

			}
		}

		return con;
	}

}
