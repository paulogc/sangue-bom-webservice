package br.com.bom.sangue.service;

import br.com.bom.sangue.dao.TelephoneDAO;
import br.com.bom.sangue.entities.Address;
import br.com.bom.sangue.entities.Telephone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class TelephoneService {

    TelephoneDAO telephoneDAO = new TelephoneDAO();

    private static final Logger LOGGER = LoggerFactory.getLogger(AddressService.class);

    public Telephone create(Telephone telephone) throws ClassNotFoundException, SQLException {
        LOGGER.info("Saving telephone in database");

        LOGGER.info("> Number {}", telephone.getNumber());
        LOGGER.info("> Type {}", telephone.getType());
        LOGGER.info("> Ddd {}", telephone.getDdd());
        LOGGER.info("> Ddi {}", telephone.getDdd());

        return telephoneDAO.create(telephone);
    }

    public Telephone findOneById(Long id) throws ClassNotFoundException, SQLException {
        LOGGER.info("Geting one telephone by id");

        LOGGER.info("> Number {}", id);

        return telephoneDAO.findOneById(id);
    }

    public Telephone update(Telephone telephone) throws  ClassNotFoundException, SQLException {
        LOGGER.info("Updating telephone");

        LOGGER.info("> Number {}", telephone.getNumber());
        LOGGER.info("> Type {}", telephone.getType());
        LOGGER.info("> Ddd {}", telephone.getDdd());
        LOGGER.info("> Ddi {}", telephone.getDdd());

        return telephoneDAO.update(telephone);
    }

    public void delete(Long id) throws  ClassNotFoundException, SQLException {
        LOGGER.info("Deleting tephone from database");

        LOGGER.info("> Number {}", id);

        telephoneDAO.delete(id);
    }
}
