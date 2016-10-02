package a.c.l.dao;

import a.c.l.model.User;

public interface UserDao {

	User findByUserName(String username);

}