package br.com.bom.sangue.service;


import br.com.bom.sangue.dao.BloodDonatorDAO;
import br.com.bom.sangue.entities.BloodDonator;
import br.com.bom.sangue.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class BloodDonatorService {

    UserService userService = new UserService();

    BloodDonatorDAO bloodDonatorDAO = new BloodDonatorDAO();

    private static final Logger LOGGER = LoggerFactory.getLogger(BloodDonatorService.class);

    public BloodDonator create(BloodDonator bloodDonator) throws ClassNotFoundException, SQLException {
        LOGGER.info("Saving blood Donator in database");

        User user = new User(bloodDonator.getName(), bloodDonator.getEmail(), bloodDonator.getBirthdate());

        user = userService.create(user);

        return bloodDonatorDAO.create(bloodDonator, user);
    }

    public BloodDonator findOneById(Long id) throws ClassNotFoundException, SQLException {
        LOGGER.info("Getting blood Donator by id");
        BloodDonator bloodDonator = new BloodDonator();

        bloodDonator = bloodDonatorDAO.findOneById(id);

        bloodDonator = (BloodDonator) userService.findOneById(bloodDonator);

        return bloodDonator;
    }


}
