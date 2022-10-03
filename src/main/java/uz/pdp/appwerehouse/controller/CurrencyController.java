package uz.pdp.appwerehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwerehouse.entity.Currency;
import uz.pdp.appwerehouse.payload.Result;
import uz.pdp.appwerehouse.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    final
    CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    //CREAT
    @PostMapping("/addCurrency")
    public Result addCurrency(@RequestBody Currency currency){
        return currencyService.addCurrency(currency);
    }

    // GET ALL
    @GetMapping("/allCurrency")
    public List<Currency> allCurrency(){
        return currencyService.allCurrency();
    }

    // GET BY ID
    @GetMapping("/getCurrencyById/{id}")
    public Currency getCurrencyById(@PathVariable Integer id){
        return currencyService.getCurrencyById(id);
    }

    // DELETE BY ID
    @DeleteMapping("/deleteCurrencyById/{id}")
    public Result deleteCurrencyById(@PathVariable Integer id){
        return currencyService.deleteCurrencyById(id);
    }

    // UPDATE BY ID
    @PutMapping("/editCurrencyById/{id}")
    public Result editCurrencyById(@PathVariable Integer id, @RequestBody Currency currency){
        return currencyService.editCurrencyById(id, currency);
    }
}
