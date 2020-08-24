package ua.lviv.lgs.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.lviv.lgs.dao.UserDao;
import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.utils.ConectionUtils;

public class UserDaoImpl implements UserDao {

	private static String READ_ALL = "select * from users";
	private static String CREATE = "insert into users(email, password, first_name, last_name, role) values(?,?,?,?,?)";
	private static String READ_BY_ID = "select * from users where id = ?";
	private static String UPDATE_BY_ID = "update users set email = ?,password = ?, first_name = ?, last_name = ?, role = ? where id = ?";
	private static String DELETE_BY_ID = "delete from users where id = ?";

	private static String READ_BY_EMAIL = "select * from users where email = ?";
	private static String UPDATE_BY_EMAIL = "update users set password = ?, first_name = ?, last_name = ?, role = ? where email = ?";
	private static String DELETE_BY_EMAIL = "delete from users where email = ?";
	
	private static Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

	private Connection connection;
	private PreparedStatement preparedStatement;

	public UserDaoImpl() {
		try {
			this.connection = ConectionUtils.openConnection();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			LOGGER.error(e);
		}
	}

	@Override
	public User create(User user) {
		try {
			preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getFirstName());
			preparedStatement.setString(4, user.getLastName());
			preparedStatement.setString(5, user.getRole());

			preparedStatement.executeUpdate();

			ResultSet rs = preparedStatement.getGeneratedKeys();
			user.setId(1);

		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return user;
	}

	@Override
	public User read(Integer id) {
		User user = null;
		try {
			preparedStatement = connection.prepareStatement(READ_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			result.next();
			String email = result.getString("email");
			String password = result.getString("password");
			String firstName = result.getString("first_name");
			String lastName = result.getString("last_name");
			String role = result.getString("role");

			user = new User(id, email, password, firstName, lastName, role);

		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return user;
	}

	@Override
	public User update(User user) {
		try {
			preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getFirstName());
			preparedStatement.setString(4, user.getLastName());
			preparedStatement.setString(5, user.getRole());
			preparedStatement.setInt(6, user.getId());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return user;
	}

	@Override
	public void delete(Integer id) {
		try {
			preparedStatement = connection.prepareStatement(DELETE_BY_ID);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();		
		} catch (SQLException e) {
			LOGGER.error(e);
		}
	}

	@Override
	public List<User> readAll() {
		List<User> userRecords = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(READ_ALL);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {

				Integer id = result.getInt("id");
				String email = result.getString("email");
				String password = result.getString("password");
				String firstName = result.getString("first_name");
				String lastName = result.getString("last_name");
				String role = result.getString("role");

				userRecords.add(new User(id, email, password, firstName, lastName, role));
			}

		} catch (SQLException e) {
			LOGGER.error(e);
		}

		return userRecords;
	}

	@Override
	public User readByEmail(String email) {
		User user = null;
		try {
			preparedStatement = connection.prepareStatement(READ_BY_EMAIL);
			preparedStatement.setString(1, email);
			ResultSet result = preparedStatement.executeQuery();
			result.next();
			Integer id = result.getInt("id");
			String password = result.getString("password");
			String firstName = result.getString("first_name");
			String lastName = result.getString("last_name");
			String role = result.getString("role");

			user = new User(id, email, password, firstName, lastName, role);

		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return user;
	}

	@Override
	public User updateByEmail(User user) {
		try {
			preparedStatement = connection.prepareStatement(UPDATE_BY_EMAIL);

			preparedStatement.setString(1, user.getPassword());
			preparedStatement.setString(2, user.getFirstName());
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setString(4, user.getRole());
			preparedStatement.setString(5, user.getEmail());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return user;
	}

	@Override
	public void deleteByEmail(String email) {
		try {
			preparedStatement = connection.prepareStatement(DELETE_BY_EMAIL);
			preparedStatement.setString(1, email);
			preparedStatement.executeUpdate();		
		} catch (SQLException e) {
			LOGGER.error(e);
		}
	}

}
