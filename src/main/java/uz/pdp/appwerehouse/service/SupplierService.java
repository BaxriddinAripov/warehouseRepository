package uz.pdp.appwerehouse.service;
import org.springframework.stereotype.Service;
import uz.pdp.appwerehouse.entity.Supplier;
import uz.pdp.appwerehouse.payload.Result;
import uz.pdp.appwerehouse.repository.SupplierRepository;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    final
    SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    // CREAT
    public Result addSupplier(Supplier supplier){
        boolean exists = supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber());
        if (exists){
            return new Result("Bunday telefon raqam mavjud", false);
        }
        Supplier supplier1 = new Supplier();
        supplier1.setName(supplier.getName());
        supplier1.setPhoneNumber(supplier.getPhoneNumber());
        supplierRepository.save(supplier1);
        return new Result("Malumot muvaffaqiyatli saqlandi", true);
    }

    // GET ALL
    public List<Supplier> allSupplier(){
        return supplierRepository.findAll();
    }

    // GET BY ID
    public Supplier getSupplierById(Integer id){
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        return optionalSupplier.orElse(null);
    }

    // DELETE
    public Result deleteSupplierById(Integer id){
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (!optionalSupplier.isPresent())
            return new Result("Bunday yetkazib beruvchi mavjud emas", false);
        supplierRepository.deleteById(id);
        return new Result("Ma'lumot muvaffaqiyatli o'chirildi", true);
    }

    // UPDATE
    public Result editSupplierById(Integer id, Supplier supplier){
        boolean exists = supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber());
        if (exists){
            return new Result("Bunday telefon raqam mavjud", false);
        }
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (!optionalSupplier.isPresent())
            return new Result("Bunday yetkazib beruvchi mavjud emas", false);
        Supplier supplier1 = optionalSupplier.get();
        supplier1.setName(supplier.getName());
        supplier1.setPhoneNumber(supplier.getPhoneNumber());
        supplierRepository.save(supplier1);
        return new Result("Ma'lumot muvaffaqiyatli o'zgartirildi", true);
    }
}
