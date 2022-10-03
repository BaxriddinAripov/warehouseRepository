package uz.pdp.appwerehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwerehouse.entity.Input;
import uz.pdp.appwerehouse.payload.InputDto;
import uz.pdp.appwerehouse.payload.Result;
import uz.pdp.appwerehouse.service.InputService;

import java.util.List;

@RestController
@RequestMapping("/input")
public class InputController {

    final
    InputService inputService;

    public InputController(InputService inputService) {
        this.inputService = inputService;
    }

    // CREAT
    @GetMapping("/addInput")
    public Result addInput(@RequestBody InputDto inputDto){
        return inputService.addInput(inputDto);
    }

    // GET ALL
    @GetMapping("/allInput")
    public List<Input> allInput(){
        return inputService.allInput();
    }

    // GET BY ID
    @GetMapping("/getInputById/{id}")
    public Input getInputById(@PathVariable Integer id){
        return inputService.getInputById(id);
    }

    // DELETE BY ID
    @DeleteMapping("/deleteInputById/{id}")
    public Result deleteInputById(@PathVariable Integer id){
        return inputService.deleteInputById(id);
    }

    // UPDATE
    @PutMapping("/editInputById/{id}")
    public Result editInputById(@PathVariable Integer id, @RequestBody InputDto inputDto){
       return inputService.editInputById(id, inputDto);
    }
}
