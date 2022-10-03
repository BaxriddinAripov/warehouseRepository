package uz.pdp.appwerehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwerehouse.entity.OutputProduct;
import uz.pdp.appwerehouse.payload.OutputProductDto;
import uz.pdp.appwerehouse.payload.Result;
import uz.pdp.appwerehouse.service.OutputProductService;

import java.util.List;

@RestController
@RequestMapping("/outputProduct")
public class OutputProductController {

    final
    OutputProductService outputProductService;

    public OutputProductController(OutputProductService outputProductService) {
        this.outputProductService = outputProductService;
    }

    // CREAT
    @PostMapping("/addOutputProduct")
    public Result addOutputProduct(@RequestBody OutputProductDto outputProductDto){
        return outputProductService.addOutputProduct(outputProductDto);
    }

    // GET ALL
    @GetMapping("/allOutputProduct")
    public List<OutputProduct> allOutputProduct(){
        return outputProductService.allOutputProduct();
    }

    // GET BY ID
    @GetMapping("/getOutputProductById/{id}")
    public OutputProduct getOutputProductById(@PathVariable Integer id){
       return outputProductService.getOutputProductById(id);
    }

    //DELETE BY ID
    @DeleteMapping("/deleteOutputProductById/{id}")
    public Result deleteOutputProductById(@PathVariable Integer id){
        return outputProductService.deleteOutputProductById(id);
    }

    // UPDATE BY ID
    @PutMapping("/editOutputProductById/{id}")
    public Result editOutputProductById(@PathVariable Integer id, @RequestBody OutputProductDto outputProductDto){
        return outputProductService.editOutputProductById(id, outputProductDto);
    }
}
