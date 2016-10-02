package com.bepza.acl.daoImpl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.bepza.acl.dao.RoleDao;
import com.bepza.acl.model.Role;

@Repository
public class RoleDaoImpl implements RoleDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	
	public void addRole(Role role) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(role);
	}

	
	@SuppressWarnings("unchecked")
	public List<Role> listRoles() {
		Session session = this.sessionFactory.getCurrentSession();
		return (List<Role>) session.createCriteria(Role.class)
				.list();
	}

	
	public Role getRole(int roleid) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Role) session.get(Role.class, roleid);
	}

	
	public void deleteRole(Role role) {
		Session session = this.sessionFactory.getCurrentSession();
		session.createQuery("DELETE FROM Role WHERE id =" + role.getId())
				.executeUpdate();
	}

	
	public Role findByType(String type) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Role) session.get(Role.class, type);
	}

}
