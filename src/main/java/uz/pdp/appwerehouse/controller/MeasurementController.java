package uz.pdp.appwerehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwerehouse.payload.Result;
import uz.pdp.appwerehouse.entity.Measurement;
import uz.pdp.appwerehouse.service.MeasurementService;
import java.util.List;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    final
    MeasurementService measurementService;

    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    // CREAT
    @PostMapping("/addMeasurement")
    public Result addMeasurement(@RequestBody Measurement measurement){
        return measurementService.addMeasurement(measurement);
    }

    // GET ALL
    @GetMapping("/allMeasurement")
    public List<Measurement> allMeasurement(){
        return measurementService.allMeasurement();
    }

    // GET BY ID
    @GetMapping("/getMeasurementById/{id}")
    public Measurement getMeasurementById(@PathVariable Integer id){
        return measurementService.getMeasurementById(id);
    }

    // DELETE BY ID
    @DeleteMapping("/deleteMeasurementById/{id}")
    public Result deleteMeasurementById(@PathVariable Integer id){
        return measurementService.deleteMeasurementById(id);
    }
}
