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

        LOGGER.info("> Bloood type {}", bloodDonator.getBloodType());
        LOGGER.info("> Bloood factor {}", bloodDonator.getBloodFactor());
        LOGGER.info("> Bloood CPF {}", bloodDonator.getCpf());
        LOGGER.info("> Bloood nick name {}", bloodDonator.getNickname());

        User user = new User(bloodDonator.getName(), bloodDonator.getEmail(),
                bloodDonator.getBirthDate(), bloodDonator.getAddress(), bloodDonator.getTelephone());

        user = userService.create(user);

        return bloodDonatorDAO.create(bloodDonator, user);
    }

    public BloodDonator findOneById(Long id) throws ClassNotFoundException, SQLException {
        LOGGER.info("Getting Blood Donator by id");
        BloodDonator bloodDonator;
        User user = new User();

        bloodDonator = bloodDonatorDAO.findOneById(id);
        user = userService.findOneById(id);

        bloodDonator = new BloodDonator(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getBirthDate(),
                user.getAddress(),
                user.getTelephone(),
                bloodDonator.getBloodType(),
                bloodDonator.getBloodFactor(),
                bloodDonator.getCpf(),
                bloodDonator.getNickname()
        );

        return bloodDonator;
    }

    public BloodDonator update(BloodDonator bloodDonator) throws ClassNotFoundException, SQLException {
        LOGGER.info("Updating Blood Donator");

        LOGGER.info("> Bloood type {}", bloodDonator.getBloodType());
        LOGGER.info("> Bloood factor {}", bloodDonator.getBloodFactor());
        LOGGER.info("> Bloood CPF {}", bloodDonator.getCpf());
        LOGGER.info("> Bloood nick name {}", bloodDonator.getNickname());

        User user = new User(bloodDonator.getId(), bloodDonator.getName(), bloodDonator.getEmail(),
                bloodDonator.getBirthDate(), bloodDonator.getAddress(), bloodDonator.getTelephone());

        user = userService.update(user);

        bloodDonator = bloodDonatorDAO.update(bloodDonator);

        return bloodDonator;
    }

    public void delete(BloodDonator bloodDonator) throws ClassNotFoundException, SQLException {
        LOGGER.info("Deleting Blood Donator");

        LOGGER.info("> Bloood nick name {}", bloodDonator.getNickname());

        User user = new User(bloodDonator.getId(), bloodDonator.getName(), bloodDonator.getEmail(),
                bloodDonator.getBirthDate(), bloodDonator.getAddress(), bloodDonator.getTelephone());

        bloodDonatorDAO.delete(bloodDonator.getId());

        userService.delete(user);
    }

    public BloodDonator findOneByCpf(String cpf) throws ClassNotFoundException, SQLException {
    	return bloodDonatorDAO.findOneByCpf(cpf);
    }
}

