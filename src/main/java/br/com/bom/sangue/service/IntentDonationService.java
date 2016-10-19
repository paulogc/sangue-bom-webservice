package br.com.bom.sangue.service;

import br.com.bom.sangue.dao.IntentDonationDAO;
import br.com.bom.sangue.entities.IntentDonation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class IntentDonationService {
    IntentDonationDAO intentDonationDAO = new IntentDonationDAO();

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    public IntentDonation create(IntentDonation intentDonation) throws ClassNotFoundException, SQLException {
        LOGGER.info("Creating Intent Donation");

        LOGGER.info("> CreatedDate {}", intentDonation.getCreatedDate());
        LOGGER.info("> GrantDate {}", intentDonation.getGrantDate());
        LOGGER.info("> Active {}", intentDonation.getActive());
        LOGGER.info("> BloodDonator {}", intentDonation.getBloodDonator().getId());

        return intentDonationDAO.create(intentDonation);
    }

    public IntentDonation findOneById(Long id) throws ClassNotFoundException, SQLException {
        LOGGER.info("Find one by id");

        LOGGER.info("> Id {}", id);

        return intentDonationDAO.findOneById(id);
    }

    public List<IntentDonation> findAllIntentDonation() throws ClassNotFoundException, SQLException {
        LOGGER.info("Finding all intent conations");

        List<IntentDonation> intentDonationList = intentDonationDAO.findAllIntentDonation();

        return intentDonationList;
    }

    public IntentDonation update(IntentDonation intentDonation) throws ClassNotFoundException, SQLException {
        LOGGER.info("Updating Intent Donation");

        LOGGER.info("> CreatedDate {}", intentDonation.getCreatedDate());
        LOGGER.info("> GrantDate {}", intentDonation.getGrantDate());
        LOGGER.info("> Active {}", intentDonation.getActive());
        LOGGER.info("> BloodDonator {}", intentDonation.getBloodDonator().getId());

        return intentDonationDAO.update(intentDonation);
    }

    public void delete (Long id) throws ClassNotFoundException, SQLException {
        LOGGER.info("Deleting Intent Donation");

        LOGGER.info("> Id {}", id);

        intentDonationDAO.delete(id);
    }
}
