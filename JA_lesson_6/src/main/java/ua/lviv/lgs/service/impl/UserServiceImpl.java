package ua.lviv.lgs.service.impl;

import java.util.List;

import ua.lviv.lgs.dao.UserDao;
import ua.lviv.lgs.dao.impl.UserDaoImpl;
import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.service.UserService;

public class UserServiceImpl implements UserService{
	
	public static UserService userServiceImpl;
	private UserDao userDao;
	
	private UserServiceImpl(){		
			userDao = new UserDaoImpl();
	}
	
	public static UserService getUserService() {		
		if(userServiceImpl == null) {
			userServiceImpl = new UserServiceImpl();
		}		
		return userServiceImpl;		
	}

	@Override
	public User create(User user) {
		return userDao.create(user);
	}

	@Override
	public User read(Integer id) {
		return userDao.read(id);
	}

	@Override
	public User update(User user) {
		return userDao.update(user);
	}

	@Override
	public void delete(Integer id) {
		userDao.delete(id);
	}

	@Override
	public List<User> readAll() {
		return userDao.readAll();
	}

	@Override
	public User readByEmail(String email) {
		return userDao.readByEmail(email);
	}

	@Override
	public User updateByEmail(User user) {
		return userDao.updateByEmail(user);
	}

	@Override
	public void deleteByEmail(String email) {		
			userDao.deleteByEmail(email);		
	}

	

}
