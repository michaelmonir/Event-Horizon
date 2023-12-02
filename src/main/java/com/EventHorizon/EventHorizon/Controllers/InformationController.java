package com.EventHorizon.EventHorizon.Controllers;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UpdateInformationDTO;
import com.EventHorizon.EventHorizon.DTOs.UserDto.ViewInformationDTO;
import com.EventHorizon.EventHorizon.Services.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/information/")
@RestController
@CrossOrigin("*")

public class InformationController {
    @Autowired
    private InformationService informationService;
    @GetMapping("getInformationViewDto")
    public ResponseEntity<ViewInformationDTO>getInformation(@RequestParam Integer id){
        return new ResponseEntity<ViewInformationDTO>(new ViewInformationDTO(informationService.getByID(id)), HttpStatus.OK);
    }
    @GetMapping("getInformationUpdateDto")
    public ResponseEntity<UpdateInformationDTO>getInformationUpdateDto(@RequestParam Integer id){
        return new ResponseEntity<UpdateInformationDTO>(new UpdateInformationDTO(informationService.getByID(id)), HttpStatus.OK);
    }
    @PutMapping("updateInformation")
    public ResponseEntity<ViewInformationDTO>updateInformation(@RequestBody UpdateInformationDTO updateInformationDTO){
        return new ResponseEntity<ViewInformationDTO>(informationService.updateWithDto(updateInformationDTO), HttpStatus.OK);
    }
}
