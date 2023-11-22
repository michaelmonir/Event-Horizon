package com.EventHorizon.EventHorizon.Controller;

import com.EventHorizon.EventHorizon.DTO.ClientDTO;
import com.EventHorizon.EventHorizon.entity.Client;
import com.EventHorizon.EventHorizon.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/client")
@RestController
public class ClientController {

    private UserService clientService;

    @Autowired
    public ClientController(UserService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients/{id}")
    public ClientDTO findById(@PathVariable Integer id) {
        Client client = clientService.getByID(id);
        if (client != null) {
            return ClientDTO.toDTO(client);
        } else {
            System.out.println("cant find this client");
            return null;
        }
    }

    @PostMapping("/clients")    //// need mapper dto
    public void addEmployee(@RequestBody Client c1) {
        clientService.add(c1);
    }

    @PutMapping("/clients") //// need mapper dto
    public void updateEmployee(@RequestBody Client c1) {
        clientService.update(c1.getId(), c1);
    }

    @DeleteMapping("/clients/{id}")
    public void delete(@PathVariable Integer id) {
        Client c1 = clientService.getByID(id);
        if (c1 == null) {
            throw new RuntimeException();
        }
        clientService.delete(id);
    }
}
