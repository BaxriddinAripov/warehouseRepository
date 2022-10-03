package uz.pdp.appwerehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwerehouse.entity.Client;
import uz.pdp.appwerehouse.entity.Currency;
import uz.pdp.appwerehouse.entity.Output;
import uz.pdp.appwerehouse.entity.Warehouse;
import uz.pdp.appwerehouse.payload.OutputDto;
import uz.pdp.appwerehouse.payload.Result;
import uz.pdp.appwerehouse.repository.ClientRepository;
import uz.pdp.appwerehouse.repository.CurrencyRepository;
import uz.pdp.appwerehouse.repository.OutputRepository;
import uz.pdp.appwerehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OutputService {

    final
    OutputRepository outputRepository;
    final
    WarehouseRepository warehouseRepository;
    final
    CurrencyRepository currencyRepository;
    final
    ClientRepository clientRepository;

    public OutputService(OutputRepository outputRepository,
                         WarehouseRepository warehouseRepository,
                         CurrencyRepository currencyRepository,
                         ClientRepository clientRepository) {
        this.outputRepository = outputRepository;
        this.warehouseRepository = warehouseRepository;
        this.currencyRepository = currencyRepository;
        this.clientRepository = clientRepository;
    }

    // CREAT
    public Result addOutput(OutputDto outputDto){
        boolean exists = outputRepository.existsByFactureNameAndWarehouse_Id(outputDto.getFactureName(), outputDto.getWarehouseId());
        if (exists){
            return new Result("Bunday faktura va ombor mavjud", false);
        }
        // WAREHOUSE
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("Bunday ombor mavjud emas", false);
        // CURRENCY
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("Bunday pul birligi mavjud emas", false);
        // CLIENT
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (!optionalClient.isPresent())
            return new Result("Bunday mijoz mavjud emas", false);
        Output output = new Output();
        output.setDate(outputDto.getDate());
        output.setWarehouse(optionalWarehouse.get());
        output.setCurrency(optionalCurrency.get());
        output.setFactureName(outputDto.getFactureName());
        output.setClient(optionalClient.get());
        outputRepository.save(output);
        output.setCode(String.valueOf(output.getId()));
        outputRepository.save(output);
        return new Result("Ma'lumot muvaffaqiyatli saqlandi", true);
    }

    // GET ALL
    public List<Output> allOutput(){
        return outputRepository.findAll();
    }

    // GET BY ID
    public Output getOutputById(Integer id){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        return optionalOutput.orElse(null);
    }

    // DELETE BY ID
    public Result deleteOutputById(Integer id){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (!optionalOutput.isPresent())
            return new Result("Bunday ma'lumot mavjud emas", false);
        outputRepository.deleteById(id);
        return new Result("Ma'lumot muvaffaqiyatli o'chirildi", true);
    }

    // UPDATE BY ID
    public Result editOutputById(Integer id, OutputDto outputDto){
        boolean exists = outputRepository.existsByFactureNameAndWarehouse_Id(outputDto.getFactureName(), outputDto.getWarehouseId());
        if (exists){
            return new Result("Bunday faktura va ombor mavjud", false);
        }
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (!optionalOutput.isPresent())
            return new Result("Bunday output mavjud emas", false);
        //WAREHOUSE
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("Bunday ombor mavjud emas", false);
        //CURRENCY
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("Bunday pul birligi mavjud emas", false);
        // CLIENT
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (!optionalClient.isPresent())
            return new Result("Bunday mijoz mavjud emas", false);
        Output output = optionalOutput.get();
        output.setDate(outputDto.getDate());
        output.setWarehouse(optionalWarehouse.get());
        output.setCurrency(optionalCurrency.get());
        output.setFactureName(outputDto.getFactureName());
        output.setClient(optionalClient.get());
        outputRepository.save(output);
        return new Result("Ma'lumot muvaffaqiyatli saqlandi", true);
    }
}
