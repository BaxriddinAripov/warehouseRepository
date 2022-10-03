package uz.pdp.appwerehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwerehouse.entity.Currency;
import uz.pdp.appwerehouse.entity.Input;
import uz.pdp.appwerehouse.entity.Supplier;
import uz.pdp.appwerehouse.entity.Warehouse;
import uz.pdp.appwerehouse.payload.InputDto;
import uz.pdp.appwerehouse.payload.Result;
import uz.pdp.appwerehouse.repository.CurrencyRepository;
import uz.pdp.appwerehouse.repository.InputRepository;
import uz.pdp.appwerehouse.repository.SupplierRepository;
import uz.pdp.appwerehouse.repository.WarehouseRepository;
import java.util.List;
import java.util.Optional;

@Service
public class InputService {

    final
    InputRepository inputRepository;
    final
    WarehouseRepository warehouseRepository;
    final
    SupplierRepository supplierRepository;
    final
    CurrencyRepository currencyRepository;

    public InputService(InputRepository inputRepository, WarehouseRepository warehouseRepository, SupplierRepository supplierRepository, CurrencyRepository currencyRepository) {
        this.inputRepository = inputRepository;
        this.warehouseRepository = warehouseRepository;
        this.supplierRepository = supplierRepository;
        this.currencyRepository = currencyRepository;
    }

    // CREAT
    public Result addInput(InputDto inputDto){
        boolean exists = inputRepository.existsByFactureNameAndWarehouse_IdAndSupplier_Id(inputDto.getFactureName(), inputDto.getWarehouseId(), inputDto.getSupplierId());
        if (exists){
            return new Result("Bunday ma'lumotlar mavjud", true);
        }
        //WAREHOUSE
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("Bunday ombor mavjud emas", false);
        // SUPPLIER
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (!optionalSupplier.isPresent())
            return new Result("Bunday yetkazib beruvchi mavjud emas", false);
        //CURRENCY
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("Bunday pul birligi mavjud emas", false);

        Input input = new Input();
        input.setDate(inputDto.getDate());
        input.setWarehouse(optionalWarehouse.get());
        input.setSupplier(optionalSupplier.get());
        input.setCurrency(optionalCurrency.get());
        input.setFactureName(inputDto.getFactureName());
        inputRepository.save(input);
        input.setCode(String.valueOf(input.getId()));
        inputRepository.save(input);
        return new Result("Ma'lumot muvaffaqiyatli saqlandi", true);
    }

    // GET ALL
    public List<Input> allInput(){
        return inputRepository.findAll();
    }

    // GET BY ID
    public Input getInputById(Integer id){
        Optional<Input> optionalInput = inputRepository.findById(id);
        return optionalInput.orElse(null);
    }

    // DELETE BY ID
    public Result deleteInputById(Integer id){
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (!optionalInput.isPresent())
            return new Result("Bunday ma'lumot mavjud emas", false);
        inputRepository.deleteById(id);
        return new Result("Malumot muvaffaqiyatli o'chirildi", true);
    }

    // UPDATE BY ID
    public Result editInputById( Integer id, InputDto inputDto) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (!optionalInput.isPresent())
            return new Result("Bunday ma'lumot mavjud emas", false);

        //WAREHOUSE
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("Bunday ombor mavjud emas", false);
        //SUPPLIER
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (!optionalSupplier.isPresent())
            return new Result("Bunday yetkazib beruvchi mavjud emas", false);
        //CURRENCY
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("Bunday pul birligi mavjud emas", false);
        Input input = new Input();
        input.setDate(inputDto.getDate());
        input.setWarehouse(optionalWarehouse.get());
        input.setSupplier(optionalSupplier.get());
        input.setCurrency(optionalCurrency.get());
        input.setFactureName(inputDto.getFactureName());
        inputRepository.save(input);
        return new Result("Ma'lumot muvaffaqiyatli o'zgartirildi", true);
    }
}
