package ua.lviv.lgs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.domain.UserRole;
import ua.lviv.lgs.service.UserService;
import ua.lviv.lgs.service.impl.UserServiceImpl;

@WebServlet(value = "/registration")
public class UserRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 2L; 
	
	private static Logger LOGGER = Logger.getLogger(UserRegistrationServlet.class);
	
	private UserService userService = UserServiceImpl.getUserService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("registration do get");
		try {
			request.getRequestDispatcher("registrationForm.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			LOGGER.error(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("regis dopost");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String role = request.getParameter("role");
		
		if(role == null) {
			role = UserRole.USER.toString();
		}
		
		if((email != null) && (password) != null && (firstName != null) && (lastName != null)) {
			
			if(userService.readByEmail(email) == null) {
				
				userService.create(new User(email, password, firstName, lastName, role));
				request.setAttribute("email", email);
				try {
					request.getRequestDispatcher("cabinet.jsp").forward(request, response);
				} catch (ServletException | IOException e) {
					LOGGER.error(e);
				}
				
			} else {
				try {
					request.getRequestDispatcher("registrationForm.jsp").forward(request, response);
				} catch (ServletException | IOException e) {
					LOGGER.error(e);
				}
			}
		}
		
	}

}
