package com.ibcs.acl.db;

/**
 *
 * @author Ahasanul Ashid, IBCS
 * @author Abu Taleb, IBCS
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibcs.acl.model.AclUIHolder;
import com.ibcs.acl.model.ObjectReference;
import com.ibcs.acl.model.PermissionTable;
import com.ibcs.acl.model.Roles;
import com.ibcs.acl.security.Permission;

/**
*
* @author Ahasanul Ashid, IBCS
* @author Abu Taleb, IBCS
* 
*/
public class AclManipulation {

	public static Boolean session_info = false;
	private DBAction db;

	public AclManipulation() {
		this.db = new DBAction();

	}

	public List<Object> viewAll(Class cls) {
		// db.calSP();
		List l = db.getAll(cls);
		return l;

	}

	public void add(Object o) {

		db.add(o);

	}

	public void merge(Object o) {

		db.update(o);
	}

	public Object getbyId(int id, Class cls) throws ClassNotFoundException {
		Object s = db.getById(id, cls);
		return s;
	}

	public void delete(int id, Class cls) throws ClassNotFoundException {

		db.delete(id, cls);

	}

	public List<Object> getSourceNames() {
		String sql = "select SOURCE_NAME FROM SOURCE";
		return db.GeneralSql(sql);

	}

	public List<Object> viewBySource(Class cls, String id) {

		List l = db.search(id, cls);
		return l;
	}

	public List<Object> LogIn(String pass, String id) {

		List l = db.Login(id, pass);
		return l;
	}

	public List getRoles() {
		List l = db.getAll(Roles.class);
		return l;

	}

	public List GetObjects() {
		List l = db.getAll(ObjectReference.class);
		return l;
	}

	public List<PermissionTable> getPermission(int role_id, int obj_id) {
		List<PermissionTable> l = db.getPermission(role_id, obj_id);
		return l;

	}

	// Map<String,List<String>>
	public Map<String, Permission> LoadPermission() {
		// //System.out.println("being called ");

		Map<String, Permission> PermissionHub = new HashMap<String, Permission>();
		List<Roles> role = this.getRoles();
		List<ObjectReference> objects = this.GetObjects();
		List<String> ListOfPermissions = new ArrayList<String>();

		for (int i = 0; i < role.size(); i++) {
			Permission permPut = new Permission();
			Map<String, List<String>> permissionMap = new HashMap<String, List<String>>();
			String currentRole = role.get(i).getRole();
			int currentRoleId = role.get(i).getRole_id();
			// ListOfPermissions.clear();

			for (int j = 0; j < objects.size(); j++) {
				String CurrentObject = objects.get(j).getClass_name();
				int CurrentObjectId = objects.get(j).getId();
				String CurrentObjectReference = objects.get(j)
						.getClass_reference();

				List<PermissionTable> permissions = getPermission(
						currentRoleId, CurrentObjectId);

				PermissionTable currentPermission = new PermissionTable();
				// List<String> ListOfPermissions1=new ArrayList();
				if (!permissions.isEmpty()) {
					currentPermission = permissions.get(0);
					permissionMap.put(CurrentObjectReference,
							getValues(currentPermission));

				} else {

					// //System.out.print("catch");

				}

			}
			permPut.setObjects(permissionMap);
			PermissionHub.put(currentRole, permPut);

		}

		// //System.out.println("start>>>>>"+PermissionHub.get("ROLE_ADMIN").getObjects().toString());
		return PermissionHub;
	}

	public List<String> getValues(PermissionTable currentPermission) {
		ArrayList<String> ListOfPermissions = new ArrayList<String>();

		if (currentPermission.getP_read() == 1) {

			ListOfPermissions.add("READ");

		}

		if (currentPermission.getP_write() == 1) {

			ListOfPermissions.add("WRITE");

		}

		if (currentPermission.getP_edit() == 1) {

			ListOfPermissions.add("EDIT");

		}

		if (currentPermission.getP_delete() == 1) {

			ListOfPermissions.add("DELETE");

		}

		return ListOfPermissions;
	}

	public String getRoleName(int id) {

		return db.RoleName(id).replace("[", "").replace("]", "");

	}

	public String getObjectName(int id) {

		return db.ObjectName(id).replace("[", "").replace("]", "");

	}

	public List<AclUIHolder> getAclUI() {
		List<AclUIHolder> aclObject = new ArrayList<AclUIHolder>();
		Class cls = PermissionTable.class;

		List<Object> permissionTable = db.getAll(cls);
		// //System.out.println(permissionTable.size()+"acl");
		for (int i = 0; i < permissionTable.size(); i++) {
			AclUIHolder acl = new AclUIHolder();
			PermissionTable pt = (PermissionTable) permissionTable.get(i);
			int id = pt.getP_id();
			int roleId = pt.getRole_id();
			int objectId = pt.getObject_id();
			// //System.out.println(roleId+"><><"+objectId);
			acl.setId(pt.getP_id());
			acl.setRole(getRoleName(roleId));
			acl.setObject(getObjectName(objectId));
			acl.setP_read(pt.getP_read());
			acl.setP_write(pt.getP_write());
			acl.setP_edit(pt.getP_edit());
			acl.setP_delete(pt.getP_delete());
			aclObject.add(acl);
		}

		return aclObject;
	}
	/*
	public MenuItemParentHolder MenuGeneration() {
		MenuItemParentHolder mItem = new MenuItemParentHolder();

		Class cls = Menu.class;
		DBAction dbx = new DBAction();
		//per p = new per();
		Map<String, List<Menu>> MenuMap = new HashMap<String, List<Menu>>();
		List<Object> ls = db.NongetAdminMenu();
		HashMap<String, ClassUtility> lclass = new HashMap<String, ClassUtility>();
		HashMap<String, String> lroot = new HashMap<String, String>();
		for (int i = 0; i < ls.size(); i++) {
			Menu menu = (Menu) ls.get(i);
			String menuName = menu.getParent_menu();
			if (menuName.equals("CDR MANAGEMENT")) {
				ClassUtility cu = new ClassUtility();
				cu.setClass_name(menuName);
				cu.setClass_reference(new Cdr());
				lclass.put(menuName, cu);
			}
			if (menuName.equals("ADMINISTRATION")) {
				ClassUtility cu = new ClassUtility();
				cu.setClass_name(menuName);
				cu.setClass_reference(new ACL());
				lclass.put(menuName, cu);
			}

	

		
		if (menuName.equals("Reports")) {
				ClassUtility cu = new ClassUtility();
				cu.setClass_name(menuName);
				cu.setClass_reference(new Reports());
				lclass.put(menuName, cu);
			}

			
			// //System.out.println(menuName+" is the MenuName"+"***************************************************");
			List<Menu> men = dbx.getChildMenu(menuName);
			// //System.out.println(men.get(0).getClass_reference()+"is the size of menu #################################################");

			MenuMap.put(menuName, men);

			lroot.put(menuName, menu.getRoot());

		}

		// //System.out.println("-----------------------------------------");
		for (int i = 0; i < lroot.size(); i++) {

			// //System.out.println(lroot.entrySet());

		}
		// //System.out.println("-----------------------------------------");

		mItem.setMenuMap(MenuMap);
		mItem.setRootMenus(lroot);
		mItem.setLclass(lclass);
		return mItem;
	}
*/
}
