package uz.pdp.appwerehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwerehouse.entity.Warehouse;
import uz.pdp.appwerehouse.payload.Result;
import uz.pdp.appwerehouse.service.WarehouseService;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    final
    WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    // CREAT
    @PostMapping("/addWarehouse")
    public Result addWarehouse(@RequestBody Warehouse warehouse){
        return warehouseService.addWarehouse(warehouse);
    }

    // GET ALL
    @GetMapping("/allWarehouse")
    public List<Warehouse> allWarehouse(){
        return warehouseService.allWarehouse();
    }

    // GET BY ID
    @GetMapping("/getWarehouseById/{id}")
    public Warehouse getWarehouseById(@PathVariable Integer id){
        return warehouseService.getWarehouseById(id);
    }

    // DELETE BY ID
    @DeleteMapping("/deleteWarehouseById/{id}")
    public Result deleteWarehouseById(@PathVariable Integer id){
        return warehouseService.deleteWarehouseById(id);
    }

    // UPDATE BY ID
    @PutMapping("/editWarehouseById/{id}")
    public Result editWarehouseById(@PathVariable Integer id, @RequestBody Warehouse warehouse){
        return warehouseService.editWarehouseById(id, warehouse);
    }
}
