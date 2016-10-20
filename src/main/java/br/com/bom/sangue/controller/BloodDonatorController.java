package br.com.bom.sangue.controller;

import br.com.bom.sangue.entities.BloodDonator;
import br.com.bom.sangue.service.BloodDonatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/blood-donator")
public class BloodDonatorController {

    BloodDonatorService bloodDonatorService = new BloodDonatorService();

    private static final Logger LOGGER = LoggerFactory.getLogger(BloodDonatorController.class);

    @RequestMapping
    @ResponseBody
    public BloodDonator create (@RequestBody BloodDonator bloodDonator) throws ClassNotFoundException, SQLException {
        return (bloodDonatorService.create(bloodDonator));
    }

    @GetMapping(value="/{id}")
    @ResponseBody
    public BloodDonator finOneById (@PathVariable("id") long id)
            throws ClassNotFoundException, SQLException {
        return (bloodDonatorService.findOneById(id));
    }

}
