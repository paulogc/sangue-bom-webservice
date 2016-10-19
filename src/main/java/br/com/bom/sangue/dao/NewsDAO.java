package br.com.bom.sangue.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.Statement;

import br.com.bom.sangue.config.DatabaseConnection;
import br.com.bom.sangue.entities.Address;
import br.com.bom.sangue.entities.Administrator;
import br.com.bom.sangue.entities.News;

public class NewsDAO {
	
	private String insertQuery = "INSERT INTO news (title, text, created_at, administrator_id) VALUES (?, ?, ?, ?)";
	
	private String updateQuery = "UPDATE news SET title = ?, text = ?, created_at = ?, administrator_id = ? WHERE id = ?";
	
	private String findAllOrderByCreatedAtQuery = "SELECT * FROM news ORDER BY created_at DESC";
	
	public News create (News news) throws ClassNotFoundException, SQLException {
		DatabaseConnection database = DatabaseConnection.getInstance();
		Connection connection = database.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(insertQuery);
		
		statement = createStatement(statement, news);
		
		statement.execute();
		
		return news;
	}
	
	public News update (News news) throws ClassNotFoundException, SQLException {
		DatabaseConnection database = DatabaseConnection.getInstance();
		Connection connection = database.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(updateQuery);
		
		statement = createStatement(statement, news);
		
		statement.setLong(5, news.getId());
		
		statement.executeUpdate();
		
		return news;
	}
	
	public List<News> findAllOrderByCreatedAt () throws ClassNotFoundException, SQLException {
		List<News> listNews = new ArrayList<>();
		
		DatabaseConnection database = DatabaseConnection.getInstance();
		Connection connection = database.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(findAllOrderByCreatedAtQuery);
		
		ResultSet resultSet = statement.executeQuery();
		
		while (resultSet.next()) {
			News news = new News();
			Administrator administrator = new Administrator();
			
			news.setId(resultSet.getLong("id"));
			news.setText(resultSet.getString("text"));
			news.setCreatedAt(resultSet.getDate("created_at"));
			
			administrator.setId(resultSet.getLong("administrator_id"));
			
			news.setAdministrator(administrator);
			
			listNews.add(news);
		}
		
		statement.close();
		
		return listNews;
	}	

	private PreparedStatement createStatement (PreparedStatement statement, News news) throws SQLException {
		statement.setString(1, news.getTitle());
		statement.setString(2, news.getText());
		statement.setString(3, news.getCreatedAt().toString());
		statement.setLong(4, news.getAdministrator().getId());
		
		return statement;
	}

}
