package com.project.card.Controller;

import com.project.card.Entity.DigitalCard;
import com.project.card.Entity.NfcCard;
import com.project.card.Service.CardService;
import com.project.card.Service.JwtService;
import com.project.card.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CardService cardService;

    @PostMapping({"/digitalcard"})
    public DigitalCard addDigitalCard(@RequestBody DigitalCard digitalCard){
        return cardService.adddigitalcards(digitalCard);
    }
    @PostMapping({"/nfccard"})
    public NfcCard addNfcCard(@RequestBody NfcCard nfcCard){

        return cardService.addNfcCard(nfcCard);
    }

}
