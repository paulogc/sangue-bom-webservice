package br.com.bom.sangue.service;


import br.com.bom.sangue.dao.AddressDAO;
import br.com.bom.sangue.dao.UserDAO;
import br.com.bom.sangue.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class UserService {

    UserDAO userDao = new UserDAO();
    AddressService addressService = new AddressService();
    TelephoneService telephoneService = new TelephoneService();

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    public User create(User user) throws ClassNotFoundException, SQLException {
        LOGGER.info("Saving user in database");

        user.setAddress(addressService.create(user.getAddress()));
        user.setTelephone(telephoneService.create(user.getTelephone()));

        LOGGER.info("> Name {}", user.getName());
        LOGGER.info("> Email {}", user.getEmail());
        LOGGER.info("> Birthdate {}", user.getBirthDate());
        LOGGER.info("> Address_id {}", user.getAddress().getId());
        LOGGER.info("> Telephone_id {}", user.getTelephone().getId());

        return userDao.create(user);
    }

    public  User findOneById(Long id) throws ClassNotFoundException, SQLException {
        User user;
        LOGGER.info("Getting user by id");

        LOGGER.info("> Id {}", id);

        user =  userDao.findOneById(id);
        user.setAddress(addressService.findOneById(user.getAddress().getId()));
        user.setTelephone(telephoneService.findOneById(user.getTelephone().getId()));

        return user;
    }

    public User update(User user) throws ClassNotFoundException, SQLException {
        LOGGER.info("Updating user");

        addressService.update(user.getAddress());
        telephoneService.update(user.getTelephone());

        LOGGER.info("> Name {}", user.getName());
        LOGGER.info("> Email {}", user.getEmail());
        LOGGER.info("> Birthdate {}", user.getBirthDate());
        LOGGER.info("> Address_id {}", user.getAddress().getId());
        LOGGER.info("> Telephone {}", user.getTelephone().getId());

        return userDao.update(user);
    }

    public void delete(User user) throws ClassNotFoundException, SQLException {
        LOGGER.info("Deleting user");
        LOGGER.info("> Id {}", user.getId());

        userDao.delete(user.getId());

        addressService.delete(user.getAddress().getId());
        telephoneService.delete(user.getTelephone().getId());
    }

}
