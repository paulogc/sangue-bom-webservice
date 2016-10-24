package br.com.bom.sangue.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.bom.sangue.entities.User;
import br.com.bom.sangue.service.NewsService;
import com.mysql.jdbc.Statement;

import br.com.bom.sangue.config.DatabaseConnection;
import br.com.bom.sangue.entities.Administrator;
import br.com.bom.sangue.entities.News;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewsDAO {
	
	private String insertQuery = "INSERT INTO news (title, text, created_at, administrator_id) VALUES (?, ?, ?, ?)";
	
	private String updateQuery = "UPDATE news SET title = ?, text = ?, created_at = ?, administrator_id = ? WHERE id = ?";
	
	private String findAllOrderByCreatedAtQuery = "SELECT * FROM news ORDER BY created_at DESC";

	private String findLastInsertedQuery = "SELECT MAX(id) AS id FROM news";
	
	public News create (News news) throws ClassNotFoundException, SQLException {
		DatabaseConnection database = DatabaseConnection.getInstance();
		Connection connection = database.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(insertQuery);
		
		statement = createStatement(statement, news);
		
		statement.execute();

		statement.close();

		news.setId(findLastInserted());
		
		return news;
	}
	
	public News update (News news) throws ClassNotFoundException, SQLException {
		DatabaseConnection database = DatabaseConnection.getInstance();
		Connection connection = database.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(updateQuery);

		statement = createStatement(statement, news);
		
		statement.setLong(5, news.getId());
		
		statement.executeUpdate();

		statement.close();
		
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

			news.setTitle(resultSet.getString("title"));
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
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String createdAt = dateFormat.format(news.getCreatedAt());

		statement.setString(1, news.getTitle());
		statement.setString(2, news.getText());
		statement.setString(3, createdAt);
		statement.setLong(4, news.getAdministrator().getId());
		
		return statement;
	}

	private Long findLastInserted () throws ClassNotFoundException, SQLException {
		News news = new News();

		DatabaseConnection dataBase = DatabaseConnection.getInstance();
		Connection connection = dataBase.getConnection();

		PreparedStatement statment = connection.prepareStatement(findLastInsertedQuery);

		ResultSet result  = statment.executeQuery();

		result.next();

		news.setId(result.getLong("id"));

		statment.close();

		return news.getId();

	}

}
