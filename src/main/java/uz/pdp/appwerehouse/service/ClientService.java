package uz.pdp.appwerehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwerehouse.entity.Client;
import uz.pdp.appwerehouse.payload.Result;
import uz.pdp.appwerehouse.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    final
    ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // CREAT
    public Result addClient(Client client){
        boolean exists = clientRepository.existsByPhoneNumber(client.getPhoneNumber());
        if (exists){
            return new Result("Bunday telefon raqam mavjud", false);
        }
        Client client1 = new Client();
        client1.setName(client.getName());
        client1.setPhoneNumber(client.getPhoneNumber());
        clientRepository.save(client1);
        return new Result("Ma'lumot muvaffaqiyatli saqlandi", true);
    }

    // GET ALL
    public List<Client> allClient(){
        return clientRepository.findAll();
    }

    // GET BY ID
    public Client getClientById(Integer id){
        Optional<Client> optionalClient = clientRepository.findById(id);
        return optionalClient.orElse(null);
    }

    // DELETE BY ID
    public Result deleteClientById(Integer id){
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (!optionalClient.isPresent())
            return new Result("Bunday mijoz mavjud emas", false);
        clientRepository.deleteById(id);
        return new Result("Malumot muvaffaqiyatli o'chirildi", true);
    }

    // UPDATE BY ID
    public Result editClientById(Integer id, Client client){
        boolean exists = clientRepository.existsByPhoneNumber(client.getPhoneNumber());
        if (exists){
            return new Result("Bunday telefon raqam mavjud", false);
        }
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (!optionalClient.isPresent())
            return new Result("Bunday mijoz mavjud emas", false);
        Client client1 = optionalClient.get();
        client1.setName(client.getName());
        client1.setPhoneNumber(client.getPhoneNumber());
        clientRepository.save(client1);
        return new Result("Ma'lumot muvaffaqiyatli o'zgartirildi", true);
    }
}
