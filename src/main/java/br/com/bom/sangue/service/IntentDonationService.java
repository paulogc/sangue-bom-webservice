package br.com.bom.sangue.service;

import br.com.bom.sangue.dao.IntentDonationDAO;
import br.com.bom.sangue.entities.BloodDonator;
import br.com.bom.sangue.entities.IntentDonation;
import br.com.bom.sangue.entities.RankingDonations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class IntentDonationService {
    IntentDonationDAO intentDonationDAO = new IntentDonationDAO();

    BloodDonatorService bloodDonatorService = new BloodDonatorService();

    private static final Logger LOGGER = LoggerFactory.getLogger(IntentDonationService.class);

    public IntentDonation create(IntentDonation intentDonation) throws ClassNotFoundException, SQLException {
        LOGGER.info("Creating Intent Donation");

        LOGGER.info("> CreatedDate {}", intentDonation.getCreatedAt());
        LOGGER.info("> GrantDate {}", intentDonation.getGrantDate());
        LOGGER.info("> Status {}", intentDonation.getStatus());
        LOGGER.info("> BloodDonator {}", intentDonation.getBloodDonator().getId());

        return intentDonationDAO.create(intentDonation);
    }

    public IntentDonation createDonator(IntentDonation intentDonation) throws ClassNotFoundException, SQLException {
        LOGGER.info("Creating Intent & Donator");

        intentDonation.setBloodDonator(bloodDonatorService.create(intentDonation.getBloodDonator()));

        return intentDonationDAO.create(intentDonation);
    }

    public List<IntentDonation> findAllByBloodDonatorId(Long id) throws ClassNotFoundException, SQLException {
        LOGGER.info("Find one by id");

        LOGGER.info("> Id {}", id);

        List<IntentDonation> intentDonations = intentDonationDAO.findAllByBloodDonatorId(id);

        for (IntentDonation intentDonation : intentDonations) {

            intentDonation.setBloodDonator(bloodDonatorService.findOneById(intentDonation.getBloodDonator().getId()));
        }

        return intentDonations;
    }

    public List<IntentDonation> findAllIntentDonation() throws ClassNotFoundException, SQLException {
        LOGGER.info("Finding all intent conations");

        List<IntentDonation> intentDonations = intentDonationDAO.findAllIntentDonation();

        for (IntentDonation intentDonation : intentDonations) {

            intentDonation.setBloodDonator(bloodDonatorService.findOneById(intentDonation.getBloodDonator().getId()));
        }

        return intentDonations;
    }

    public List<IntentDonation> findByNeighborhood(String neighborhood) throws ClassNotFoundException, SQLException {
        LOGGER.info("Finding all intent by neighborhood");

        List<IntentDonation> intentDonations = intentDonationDAO.findByNeighborhood(neighborhood);

        for (IntentDonation intentDonation : intentDonations) {

            intentDonation.setBloodDonator(bloodDonatorService.findOneById(intentDonation.getBloodDonator().getId()));
        }

        return intentDonations;
    }
    
    public IntentDonation update(IntentDonation intentDonation) throws ClassNotFoundException, SQLException {
        LOGGER.info("Updating Intent Donation");

        LOGGER.info("> CreatedDate {}", intentDonation.getCreatedAt());
        LOGGER.info("> GrantDate {}", intentDonation.getGrantDate());
        LOGGER.info("> Status {}", intentDonation.getStatus());
        LOGGER.info("> BloodDonator {}", intentDonation.getBloodDonator().getId());

        intentDonation.setBloodDonator(bloodDonatorService.findOneById(intentDonation.getBloodDonator().getId()));

        return intentDonationDAO.update(intentDonation);
    }

    public void delete (Long id) throws ClassNotFoundException, SQLException {
        LOGGER.info("Deleting Intent Donation");

        LOGGER.info("> Id {}", id);

        intentDonationDAO.delete(id);
    }
    
    public List<RankingDonations> getRankingDonations () throws ClassNotFoundException, SQLException {
    	return intentDonationDAO.findAllByRanking();
    }
}
