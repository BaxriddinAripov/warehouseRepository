package uz.pdp.appwerehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwerehouse.entity.Output;
import uz.pdp.appwerehouse.entity.OutputProduct;
import uz.pdp.appwerehouse.entity.Product;
import uz.pdp.appwerehouse.payload.OutputProductDto;
import uz.pdp.appwerehouse.payload.Result;
import uz.pdp.appwerehouse.repository.OutputProductRepository;
import uz.pdp.appwerehouse.repository.OutputRepository;
import uz.pdp.appwerehouse.repository.ProductRepository;
import java.util.List;
import java.util.Optional;

@Service
public class OutputProductService {

    final
    OutputProductRepository outputProductRepository;
    final
    ProductRepository productRepository;
    final
    OutputRepository outputRepository;

    public OutputProductService(OutputProductRepository outputProductRepository,
                                ProductRepository productRepository,
                                OutputRepository outputRepository) {
        this.outputProductRepository = outputProductRepository;
        this.productRepository = productRepository;
        this.outputRepository = outputRepository;
    }

    // CREAT
    public Result addOutputProduct(OutputProductDto outputProductDto){
        boolean exists = outputProductRepository.existsByProduct_IdAndOutput_Id(outputProductDto.getProductId(), outputProductDto.getOutputId());
        if (exists){
            return new Result("Bunday product va output mavjud", false);
        }
        // PRODUCT
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (!optionalProduct.isPresent())
            return new Result("Bunday product mavjud emas", false);
        //OUTPUT
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (!optionalOutput.isPresent())
            return new Result("Bunday output mavjud emas", false);
        OutputProduct outputProduct = new OutputProduct();
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProduct.setOutput(optionalOutput.get());
        outputProductRepository.save(outputProduct);
        return new Result("Ma'lumot muvaffaqiyatki saqlandi", true);
    }

    // GET ALL
    public List<OutputProduct> allOutputProduct(){
        return outputProductRepository.findAll();
    }

    // GET BY ID
    public OutputProduct getOutputProductById(Integer id){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        return optionalOutputProduct.orElse(null);
    }

    // DELETE BY ID
    public Result deleteOutputProductById(Integer id){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (!optionalOutputProduct.isPresent())
            return new Result("Bunday outputProduct mavjud emas", false);
        outputProductRepository.deleteById(id);
        return new Result("Ma'lumot muvaffaqiyatli o'chirildi", true);
    }

    // UPDATE BY ID
    public Result editOutputProductById(Integer id, OutputProductDto outputProductDto){
        boolean exists = outputProductRepository.existsByProduct_IdAndOutput_Id(outputProductDto.getProductId(), outputProductDto.getOutputId());
        if (exists){
            return new Result("Bunday product va output mavjud", false);
        }
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (!optionalOutputProduct.isPresent())
            return new Result("Bunday outputProduct mavjud emas", false);
        //PRODUCT
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (!optionalProduct.isPresent())
            return new Result("Bunday product mavjud emas", false);
        //OUTPUT
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if(!optionalOutput.isPresent())
            return new Result("Bunday output mavjud emas", false);
        OutputProduct outputProduct = optionalOutputProduct.get();
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProduct.setOutput(optionalOutput.get());
        outputProductRepository.save(outputProduct);
        return new Result("Ma'lumot muvaffaqiyatli o'zgartirildi", true);
    }
}
