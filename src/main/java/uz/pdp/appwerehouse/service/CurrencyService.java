package uz.pdp.appwerehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwerehouse.entity.Currency;
import uz.pdp.appwerehouse.payload.Result;
import uz.pdp.appwerehouse.repository.CurrencyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {

    final
    CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    // CREAT
    public Result addCurrency(Currency currency){
        boolean exists = currencyRepository.existsByName(currency.getName());
        if (exists){
            return new Result("Bunday pul birligi mavjud", false);
        }
        Currency currency1 = new Currency();
        currency1.setName(currency.getName());
        currencyRepository.save(currency1);
        return new Result("Ma'lumot muvaffaqiyatli saqlandi", true);
    }

    // GET ALL
    public List<Currency> allCurrency(){
        return currencyRepository.findAll();
    }

    // GET BY ID
    public Currency getCurrencyById(Integer id){
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        return optionalCurrency.orElse(null);
    }

    // DELETE BY ID
    public Result deleteCurrencyById(Integer id){
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (!optionalCurrency.isPresent())
            return new Result("Bunday pul birligi mavjud emas", false);
        currencyRepository.deleteById(id);
        return new Result("Ma'lumot muvaffaqiyatli o'chirildi", true);
    }

    // UPDATE
    public Result editCurrencyById(Integer id, Currency currency){
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (!optionalCurrency.isPresent())
            return new Result("Bunday ma'lumot mavjud emas", false);
        boolean exists = currencyRepository.existsByName(currency.getName());
        if (exists){
            return new Result("Bunday nomli pul birligi mavjud", false);
        }
        Currency currency1 = optionalCurrency.get();
        currency1.setName(currency.getName());
        currencyRepository.save(currency1);
        return new Result("Ma'lumot muvaffaqiyatli o'zgartirildi", true);
    }
}
