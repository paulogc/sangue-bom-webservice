package br.com.bom.sangue.service;

import java.sql.SQLException;
import java.util.List;

import br.com.bom.sangue.entities.RankingDonations;

public class RankingDonationsService {
	
	IntentDonationService intentDonationService = new IntentDonationService();
	
	public List<RankingDonations> getRankingDonation () throws ClassNotFoundException, SQLException {
		return intentDonationService.getRankingDonations();
	}

}
