package com.EventHorizon.EventHorizon.Controller;

import com.EventHorizon.EventHorizon.DTO.UpdateInformationDTO;
import com.EventHorizon.EventHorizon.DTO.ViewInformationDTO;
import com.EventHorizon.EventHorizon.services.ClientService;
import com.EventHorizon.EventHorizon.services.InformationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/information")
@RestController

public class InformationController {

    private InformationService informationService;
    @GetMapping("/getInformationViewDto")
    public ResponseEntity<ViewInformationDTO>getInformation(@RequestParam String email){
        return new ResponseEntity<ViewInformationDTO>(informationService.getViewInformationDTO(informationService.getByEmail(email)), HttpStatus.OK);
    }
    @GetMapping("/getInformationUpdateDto")
    public ResponseEntity<UpdateInformationDTO>getInformationUpdateDto(@RequestParam String email){
        return new ResponseEntity<UpdateInformationDTO>(new UpdateInformationDTO(informationService.getByEmail(email)), HttpStatus.OK);
    }
    @PutMapping("/updateInformation")
    public ResponseEntity<ViewInformationDTO>updateInformation(@RequestBody UpdateInformationDTO updateInformationDTO){
        return new ResponseEntity<ViewInformationDTO>(informationService.updateWithDto(updateInformationDTO), HttpStatus.OK);
    }
}
