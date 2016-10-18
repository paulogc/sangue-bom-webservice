package br.com.bom.sangue.service;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.bom.sangue.dao.NewsDAO;
import br.com.bom.sangue.entities.News;

/**
 * Created by paulo on 02/10/16.
 */
public class NewsService {
	
	NewsDAO newsDAO = new NewsDAO();
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TelephoneService.class);
	
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
	
}
