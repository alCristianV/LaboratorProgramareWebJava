package com.example.carrental.controller;

import com.example.carrental.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/offers")
@Validated
public class OfferController {

    @Autowired
    private OfferService service;


}
