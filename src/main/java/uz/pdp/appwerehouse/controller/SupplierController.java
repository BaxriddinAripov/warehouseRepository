package uz.pdp.appwerehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwerehouse.entity.Supplier;
import uz.pdp.appwerehouse.payload.Result;
import uz.pdp.appwerehouse.service.SupplierService;
import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    final
    SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    //CREAT
    @PostMapping("/addSupplier")
    public Result addSupplier(@RequestBody Supplier supplier){
        return supplierService.addSupplier(supplier);
    }

    // GET ALL
    @GetMapping("/allSupplier")
    public List<Supplier> allSupplier(){
        return supplierService.allSupplier();
    }

    // GET BY ID
    @GetMapping("/getSupplierById/{id}")
    public Supplier getSupplierById(@PathVariable Integer id){
        return supplierService.getSupplierById(id);
    }

    // DELETE
    @DeleteMapping("/deleteSupplierById/{id}")
    public Result deleteSupplierById(@PathVariable Integer id){
        return supplierService.deleteSupplierById(id);
    }

    // UPDATE BY ID
    @PutMapping("/editSupplierById/{id}")
    public Result editSupplierById(@PathVariable Integer id, @RequestBody Supplier supplier){
        return supplierService.editSupplierById(id, supplier);
    }
}
