package br.com.bom.sangue.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.bom.sangue.dao.NewsDAO;
import br.com.bom.sangue.entities.News;

public class NewsService {
	
	NewsDAO newsDAO = new NewsDAO();
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NewsService.class);
	
	public News create (News news) throws ClassNotFoundException, SQLException {
		LOGGER.info("Saving news in database");
		
		LOGGER.info("> Title {}", news.getTitle());
		LOGGER.info("> Text {}", news.getText());
		LOGGER.info("> Created at {}", news.getCreatedAt());
		LOGGER.info("> Administrator {}", news.getAdministrator().getId());
		
		return newsDAO.create(news);
	}
	
	public News update (News news) throws ClassNotFoundException, SQLException {
		LOGGER.info("Updating news in database");
		
		LOGGER.info("> Title {}", news.getTitle());
		LOGGER.info("> Text {}", news.getText());
		LOGGER.info("> Created at {}", news.getCreatedAt());
		LOGGER.info("> Administrator {}", news.getAdministrator().getId());
		
		return newsDAO.update(news);
	}
	
	public List<News> findAllOrderByCreatedAt () throws ClassNotFoundException, SQLException {
		LOGGER.info("Find all order by created at");
		
		return newsDAO.findAllOrderByCreatedAt();
	}
	
}
