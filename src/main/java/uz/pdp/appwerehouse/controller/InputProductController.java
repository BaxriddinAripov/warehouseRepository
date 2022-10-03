package uz.pdp.appwerehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwerehouse.entity.InputProduct;
import uz.pdp.appwerehouse.payload.InputProductDto;
import uz.pdp.appwerehouse.payload.Result;
import uz.pdp.appwerehouse.service.InputProductService;
import java.util.List;

@RestController
@RequestMapping("/inputProduct")
public class InputProductController {

    final
    InputProductService inputProductService;

    public InputProductController(InputProductService inputProductService) {
        this.inputProductService = inputProductService;
    }


    // CREAT
    @PostMapping("/addInputProduct")
    public Result addInputProduct(@RequestBody InputProductDto inputProductDto){
        return inputProductService.addInputProduct(inputProductDto);
    }

    // GET ALL
    @GetMapping("/allInputProduct")
    public List<InputProduct> allInputProduct(){
        return inputProductService.allInputProduct();
    }

    // GET BY ID
    @GetMapping("/getInputProductById/{id}")
    public InputProduct getInputProductById(@PathVariable Integer id){
        return inputProductService.getInputProductById(id);
    }

    //DELETE BY ID
    @DeleteMapping("/deleteInputProductById/{id}")
    public Result deleteInputProductById(@PathVariable Integer id){
        return inputProductService.deleteInputProductById(id);
    }

    // UPDATE BY ID
    @PutMapping("/editInputProductById/{id}")
    public Result editInputProductById(@PathVariable Integer id, @RequestBody InputProductDto inputProductDto){
        return inputProductService.editInputProductById(id, inputProductDto);
    }
}
