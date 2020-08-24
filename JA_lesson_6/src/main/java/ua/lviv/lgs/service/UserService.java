package ua.lviv.lgs.service;

import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.shared.AbstractCRUD;

public interface UserService extends AbstractCRUD <User>{
	
	User readByEmail(String email);
	User updateByEmail(User user);
	void deleteByEmail(String email);

}
