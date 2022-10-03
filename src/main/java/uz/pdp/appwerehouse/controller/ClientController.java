package uz.pdp.appwerehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwerehouse.entity.Client;
import uz.pdp.appwerehouse.payload.Result;
import uz.pdp.appwerehouse.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    final
    ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    //CREAT
    @PostMapping("/addClient")
    public Result addClient(@RequestBody Client client){
        return clientService.addClient(client);
    }

    // GET ALL
    @GetMapping("/allClient")
    public List<Client> allClient(){
        return clientService.allClient();
    }

    // GET BY ID
    @GetMapping("/getClientById/{id}")
    public Client getClientById(@PathVariable Integer id){
        return clientService.getClientById(id);
    }

    // DELETE BY ID
    @DeleteMapping("/deleteClientById/{id}")
    public Result deleteClientById(@PathVariable Integer id){
        return clientService.deleteClientById(id);
    }

    // UPDATE BY ID
    @PutMapping("/editClientById/{id}")
    public Result editClientById(@PathVariable Integer id, @RequestBody Client client){
        return clientService.editClientById(id, client);
    }
}
