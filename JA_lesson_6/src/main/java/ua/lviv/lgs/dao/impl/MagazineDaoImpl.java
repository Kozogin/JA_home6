package ua.lviv.lgs.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.lviv.lgs.dao.MagazineDao;
import ua.lviv.lgs.domain.Magazine;
import ua.lviv.lgs.utils.ConectionUtils;

public class MagazineDaoImpl implements MagazineDao{
	
	private static String READ_ALL = "select * from magazine";
	private static String CREATE = "insert into magazine(name, description, price, isbn) values(?,?,?,?)";
	private static String READ_BY_ID = "select * from magazine where id = ?";
	private static String UPDATE_BY_ID = "update magazine set name = ?, description = ?, price = ?, isbn = ? where id = ?";
	private static String DELETE_BY_ID = "delete from magazine where id = ?";

	private Connection connection; 
	private PreparedStatement preparedStatement;
	
	private static Logger LOGGER = Logger.getLogger(MagazineDaoImpl.class);
	
	public MagazineDaoImpl() {
		try {
			this.connection = ConectionUtils.openConnection();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			LOGGER.error(e);
		}
	}
	
	@Override
	public Magazine create(Magazine magazine) {		
		try {
			preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);	
			preparedStatement.setString(1, magazine.getName());
			preparedStatement.setString(2, magazine.getDescription());				
			preparedStatement.setDouble(3, magazine.getPrice());	
			
			preparedStatement.executeUpdate();
			
			ResultSet rs = preparedStatement.getGeneratedKeys();
			magazine.setId(1);
			
			} catch (java.sql.SQLException e) {
				LOGGER.error(e);
			}
			return magazine;
	}

	@Override
	public Magazine read(Integer id) {		
		Magazine magazine = null;
		try {
			preparedStatement = connection.prepareStatement(READ_BY_ID);
			preparedStatement.setInt(1, id); 
			ResultSet result = preparedStatement.executeQuery();
			result.next();
			String name =  result.getString("name");
			String description =  result.getString("description");
			Double price = result.getDouble("price");
			String isbn =  result.getString("isbn");			
			
			magazine = new Magazine(name, description, price, isbn);
			
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return magazine;
	}

	@Override
	public Magazine update(Magazine magazine) {
		try {
			preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
			preparedStatement.setString(1, magazine.getName());
			preparedStatement.setString(2, magazine.getDescription());
			preparedStatement.setDouble(3, magazine.getPrice());
			preparedStatement.setString(4, magazine.getIsbn());				
			preparedStatement.setInt(5, magazine.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e);
		}	
		return magazine;
	}

	@Override
	public void delete(Integer id){		
		try {
			preparedStatement = connection.prepareStatement(DELETE_BY_ID);				
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();		
		} catch (SQLException e) {
			LOGGER.error(e);
		}
	}

	@Override
	public List<Magazine> readAll() {
		List<Magazine> magazineRecords = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(READ_ALL);
			ResultSet result = preparedStatement.executeQuery();
				while(result.next()) {
					String name =  result.getString("name");
					String description =  result.getString("description");
					Double price = result.getDouble("price");
					String isbn =  result.getString("isbn");
					
					magazineRecords.add(new Magazine(name, description, price, isbn));
				}			
			
		} catch (SQLException e) {
			LOGGER.error(e);
		}		
		return magazineRecords;
	}

}
