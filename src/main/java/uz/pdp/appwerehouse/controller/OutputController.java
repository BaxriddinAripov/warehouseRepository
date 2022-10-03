package uz.pdp.appwerehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwerehouse.entity.Output;
import uz.pdp.appwerehouse.payload.OutputDto;
import uz.pdp.appwerehouse.payload.Result;
import uz.pdp.appwerehouse.service.OutputService;

import java.util.List;

@RestController
@RequestMapping("/output")
public class OutputController {

    final
    OutputService outputService;

    public OutputController(OutputService outputService) {
        this.outputService = outputService;
    }

    // CREAT
    @PostMapping("/addOutput")
    public Result addOutput(@RequestBody OutputDto outputDto) {
        return outputService.addOutput(outputDto);
    }

    // GET ALL
    @GetMapping("/allOutput")
    public List<Output> allOutput() {
        return outputService.allOutput();
    }

    // GET BY ID
    @GetMapping("/getOutputById/{id}")
    public Output getOutputById(@PathVariable Integer id) {
        return outputService.getOutputById(id);
    }

    //DELETE BY ID
    @DeleteMapping("/deleteOutputById/{id}")
    public Result deleteOutputById(@PathVariable Integer id) {
        return outputService.deleteOutputById(id);
    }

    // UPDATE BY ID
    @PutMapping("/editOutputById/{id}")
    public Result editOutputById(@PathVariable Integer id, @RequestBody OutputDto outputDto) {
        return outputService.editOutputById(id, outputDto);
    }
}
