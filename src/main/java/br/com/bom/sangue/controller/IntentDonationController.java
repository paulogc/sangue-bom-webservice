package br.com.bom.sangue.controller;

import br.com.bom.sangue.entities.IntentDonation;
import br.com.bom.sangue.service.IntentDonationService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/intent-donation")
public class IntentDonationController {

    IntentDonationService intentDonationService = new IntentDonationService();

    @PostMapping
    @ResponseBody
    public IntentDonation create (@RequestBody IntentDonation intentDonation) throws ClassNotFoundException, SQLException {
        return intentDonationService.create(intentDonation);
    }
}
