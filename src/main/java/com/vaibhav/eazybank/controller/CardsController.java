package com.vaibhav.eazybank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardsController {
    @GetMapping("/card")
    public String getCards()
    {
        return "returning card details";
    }
}
