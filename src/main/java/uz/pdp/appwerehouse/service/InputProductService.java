package uz.pdp.appwerehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwerehouse.entity.Input;
import uz.pdp.appwerehouse.entity.InputProduct;
import uz.pdp.appwerehouse.entity.Product;
import uz.pdp.appwerehouse.payload.InputProductDto;
import uz.pdp.appwerehouse.payload.Result;
import uz.pdp.appwerehouse.repository.InputProductRepository;
import uz.pdp.appwerehouse.repository.InputRepository;
import uz.pdp.appwerehouse.repository.ProductRepository;
import java.util.List;
import java.util.Optional;

@Service
public class InputProductService {

    final
    InputProductRepository inputProductRepository;
    final
    ProductRepository productRepository;
    final
    InputRepository inputRepository;

    public InputProductService(InputProductRepository inputProductRepository, ProductRepository productRepository,
                               InputRepository inputRepository) {
        this.inputProductRepository = inputProductRepository;
        this.productRepository = productRepository;
        this.inputRepository = inputRepository;
    }
    // CREAT
    public Result addInputProduct(InputProductDto inputProductDto){
        boolean exists = inputProductRepository.existsByProduct_IdAndInput_Id(inputProductDto.getProductId(),
                inputProductDto.getInputId());
        if (exists){
            return new Result("Bunday ma'lumot mavjud", false);
        }
        // INPUT
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (!optionalInput.isPresent())
            return new Result("Bunday input mavjud emas", false);
        // PRODUCT
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (!optionalProduct.isPresent())
            return new Result("Bunday product mavjud emas", false);
        InputProduct inputProduct = new InputProduct();
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());
        inputProduct.setInput(optionalInput.get());
        inputProductRepository.save(inputProduct);
        return new Result("Ma'lumot muvaffaqiyatli saqlandi", true);
    }

    // GET ALL
    public List<InputProduct> allInputProduct(){
       return inputProductRepository.findAll();
    }

    // GET BY ID
    public InputProduct getInputProductById(Integer id){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        return optionalInputProduct.orElse(null);
    }

    // DELETE BY ID
    public Result deleteInputProductById(Integer id){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (!optionalInputProduct.isPresent())
            return new Result("Bunday product mavjud emas", false);
        inputProductRepository.deleteById(id);
        return new Result("Ma'lumot muvaffaqiyatli o'chirildi", true);
    }

    //UPDATE BY ID
    public Result editInputProductById(Integer id, InputProductDto inputProductDto){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (!optionalInputProduct.isPresent())
            return new Result("Bunday inputProduct mavjud emas", false);
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (!optionalProduct.isPresent())
            return new Result("Bunday product mavjud emas", false);
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (!optionalInput.isPresent())
            return new Result("Bunday input mavjud emas", false);
        InputProduct inputProduct = optionalInputProduct.get();
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());
        inputProduct.setInput(optionalInput.get());
        inputProductRepository.save(inputProduct);
        return new Result("Ma'lumot muvaffaqiyatli saqlandi", true);
    }
}
