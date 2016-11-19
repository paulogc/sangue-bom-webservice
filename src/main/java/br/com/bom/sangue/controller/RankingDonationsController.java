package br.com.bom.sangue.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.bom.sangue.entities.RankingDonations;
import br.com.bom.sangue.service.RankingDonationsService;

@RestController
@RequestMapping("/ranking-donations")
public class RankingDonationsController {
	
	RankingDonationsService rankingDonationsService = new RankingDonationsService();
	
	@GetMapping()
    @ResponseBody
    public List<RankingDonations> findAllByUserId () throws ClassNotFoundException, SQLException {
        return rankingDonationsService.getRankingDonation();
    }

}
