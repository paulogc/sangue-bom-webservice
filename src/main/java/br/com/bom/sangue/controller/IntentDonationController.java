package br.com.bom.sangue.controller;

import br.com.bom.sangue.entities.IntentDonation;
import br.com.bom.sangue.service.IntentDonationService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/intent-donation")
public class IntentDonationController {

    IntentDonationService intentDonationService = new IntentDonationService();

    @PostMapping
    @ResponseBody
    public IntentDonation create (@RequestBody IntentDonation intentDonation) throws ClassNotFoundException, SQLException {
        return intentDonationService.create(intentDonation);
    }

    @GetMapping(value = "/find-all-by-user={user_id}")
    @ResponseBody
    public List<IntentDonation> finsAllByUserId (@PathVariable("user_id") Long user_id) throws ClassNotFoundException, SQLException {
        return intentDonationService.findAllByBloodDonatorId(user_id);
    }
}
