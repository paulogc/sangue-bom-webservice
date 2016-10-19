package br.com.bom.sangue.service;


import br.com.bom.sangue.dao.AddressDAO;
import br.com.bom.sangue.dao.UserDAO;
import br.com.bom.sangue.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class UserService {

    UserDAO userDao = new UserDAO();
    AddressDAO addressDAO = new AddressDAO();

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    public User create(User user) throws ClassNotFoundException, SQLException {
        LOGGER.info("Saving user in database");

        LOGGER.info("> Name {}", user.getName());
        LOGGER.info("> Email {}", user.getEmail());
        LOGGER.info("> Birthdate {}", user.getBirthdate());
        LOGGER.info("> Address_d {}", user.getAddress().getId());

        return userDao.create(user);
    }

    public  User findOneById(Long id) throws ClassNotFoundException, SQLException {
        User user;
        LOGGER.info("Getting user by id");

        LOGGER.info("> Id {}", id);

        user =  userDao.findOneById(id);
        user.setAddress(addressDAO.findOneById(user.getAddress().getId()));

        return user;
    }

    public User update(User user) throws ClassNotFoundException, SQLException {
        LOGGER.info("Updating user");

        LOGGER.info("> Name {}", user.getName());
        LOGGER.info("> Email {}", user.getEmail());
        LOGGER.info("> Birthdate {}", user.getBirthdate());
        LOGGER.info("> Address_id {}", user.getAddress().getId());

        return userDao.update(user);
    }

    public void delete(Long id) throws ClassNotFoundException, SQLException {
        LOGGER.info("Deleting user");

        LOGGER.info("> Id {}", id);

        userDao.delete(id);
    }

}
