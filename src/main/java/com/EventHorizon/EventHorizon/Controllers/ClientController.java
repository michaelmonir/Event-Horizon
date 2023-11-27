package com.EventHorizon.EventHorizon.Controllers;

import com.EventHorizon.EventHorizon.Entities.UserEntities.Client;
import com.EventHorizon.EventHorizon.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/client")
@RestController
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients/{id}")
    public void findById(@PathVariable Integer id) {
        Client client = clientService.getByID(id);

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
