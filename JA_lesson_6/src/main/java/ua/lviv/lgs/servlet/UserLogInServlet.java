package ua.lviv.lgs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.service.UserService;
import ua.lviv.lgs.service.impl.UserServiceImpl;

public class UserLogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger LOGGER = Logger.getLogger(UserRegistrationServlet.class);
	
	private UserService userService = UserServiceImpl.getUserService();

	public void init() {
		System.out.println("init servlet");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doget");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("dopost");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		User user = userService.readByEmail(email);
		if (user == null) {
			try {
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				LOGGER.error(e);
			}
		}
		
		if(user.getPassword().equals(password)) {
			request.setAttribute("email", email);
			try {
				request.getRequestDispatcher("cabinet.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				LOGGER.error(e);
			}
		} else {
			try {
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				LOGGER.error(e);
			}
		}

//		if(userService.boolUserPassword(usersDao, login, password)) {
//			if(login.equals("admin")){
//				request.getRequestDispatcher("admin.jsp").forward(request, response);
//			} else {
//				boolUserLogIn = true;
//				request.setAttribute("login", login);			
//				request.getRequestDispatcher("cabinet.jsp").forward(request, response);
//			}
//		} else {
//			request.setAttribute("wrongInput", "incorrect login or password");
//			request.getRequestDispatcher("index.jsp").forward(request, response);
//		}
	}

	
}
