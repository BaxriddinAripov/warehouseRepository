package uz.pdp.appwerehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwerehouse.entity.Warehouse;
import uz.pdp.appwerehouse.payload.Result;
import uz.pdp.appwerehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {

    final
    WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }
    // CREAT
    public Result addWarehouse(Warehouse warehouse){
        boolean exists = warehouseRepository.existsByName(warehouse.getName());
        if (exists){
            return new Result("Bunday ombor mavjud", false);
        }
        Warehouse warehouse1 = new Warehouse();
        warehouse1.setName(warehouse.getName());
        warehouseRepository.save(warehouse1);
        return new Result("Ma'lumot muvaffaqiyatli saqlandi", true);
    }

    // GET ALL
    public List<Warehouse> allWarehouse(){
        return warehouseRepository.findAll();
    }

    // GET BY ID
    public Warehouse getWarehouseById(Integer id){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        return optionalWarehouse.orElse(null);
    }

    // DELETE BY ID
    public Result deleteWarehouseById(Integer id){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (!optionalWarehouse.isPresent())
            return new Result("Bunday ombor mavjud emas", false);
        warehouseRepository.deleteById(id);
        return new Result("Ma'lumot muvaffaqiyatli o'chirildi", true);
    }

    // UPDATE BY ID
    public Result editWarehouseById( Integer id, Warehouse warehouse){
        boolean exists = warehouseRepository.existsByName(warehouse.getName());
        if (exists){
            return new Result("Bunday ombor mavjud", false);
        }
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (!optionalWarehouse.isPresent())
            return new Result("Bunday ombor mavjud emas", false);
        Warehouse warehouse1 = optionalWarehouse.get();
        warehouse1.setName(warehouse.getName());
        warehouseRepository.save(warehouse1);
        return new Result("Ma'lumot muvaffaqiyatli o'zgartirildi", true);
    }
}
