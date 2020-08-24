package ua.lviv.lgs;

import java.sql.SQLException;

import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.service.UserService;
import ua.lviv.lgs.service.impl.UserServiceImpl;

public class app {
	
	public static void main(String[] args) throws SQLException {
		
		
		UserService userService = UserServiceImpl.getUserService();
		//userService.create(new User("test@test", "passtest", "firsttest", "lasttest", "userroletest"));
		System.out.println(userService.readAll());
		System.out.println(userService.read(2));
		System.out.println("by email");
		
		userService.update(
				new User(2, "55reta","55111","55feda","55fff","55dmin")
				);
		System.out.println(userService.readByEmail("greta"));
		System.out.println(userService.readByEmail("test@test"));
		System.out.println(userService.readAll());
	}

}
