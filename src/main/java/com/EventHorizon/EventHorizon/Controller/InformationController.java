package com.EventHorizon.EventHorizon.Controller;

import com.EventHorizon.EventHorizon.services.ClientService;
import com.EventHorizon.EventHorizon.services.InformationService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/information")
@RestController
public class InformationController {

    private InformationService informationService;


}
