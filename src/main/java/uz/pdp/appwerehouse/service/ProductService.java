package uz.pdp.appwerehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwerehouse.entity.Attachment;
import uz.pdp.appwerehouse.entity.Category;
import uz.pdp.appwerehouse.entity.Measurement;
import uz.pdp.appwerehouse.entity.Product;
import uz.pdp.appwerehouse.payload.ProductDto;
import uz.pdp.appwerehouse.payload.Result;
import uz.pdp.appwerehouse.repository.AttachmentRepository;
import uz.pdp.appwerehouse.repository.CategoryRepository;
import uz.pdp.appwerehouse.repository.MeasurementRepository;
import uz.pdp.appwerehouse.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    final
    ProductRepository productRepository;
    final
    CategoryRepository categoryRepository;
    final
    AttachmentRepository attachmentRepository;
    final
    MeasurementRepository measurementRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository,
                          AttachmentRepository attachmentRepository,
                          MeasurementRepository measurementRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.attachmentRepository = attachmentRepository;
        this.measurementRepository = measurementRepository;
    }



    // CREAT
    public Result addProduct(ProductDto productDto) {
        boolean exists = productRepository.existsByNameAndCategory_Id(productDto.getName(), productDto.getCategoryId());
        if (exists) {
            return new Result("Bunday maxsulot ushbu kategoriyada bor", false);
        }
        //CATEGORY
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new Result("Bunday kategoriya mavjud emas", false);
        // PHOTO
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalAttachment.isPresent())
            return new Result("Bunday rasm mavjid emas", false);
        // MEASUREMENT
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalMeasurement.isPresent())
            return new Result("Bunday o'lchov birligi mavjid emas", false);
        //PRODUCT
        Product product = new Product();
        product.setName(productDto.getName());
        product.setCategory(optionalCategory.get());
        product.setPhoto(optionalAttachment.get());
        product.setMeasurement(optionalMeasurement.get());
        productRepository.save(product);
        product.setCode(String.valueOf(product.getId()));
        productRepository.save(product);
        return new Result("Maxsulot saqlandi", true);
    }

    // GET ALL
    public List<Product> allProduct(){
        return productRepository.findAll();
    }

    // GET BY ID
    public Product getProductById(Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    //DELETE BY ID
    public Result deleteProductById(Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent())
            return new Result("Bunday maxsulot mavjud emas", false);
        productRepository.deleteById(id);
        return new Result("Maxsulot o'chirildi", true);
    }

    // UPDATE BY ID
    public Result editProductById(Integer id, ProductDto productDto){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent())
            return new Result("Bunday maxsulot mavjud emas", false);
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new Result("Bunday kategoriya mavjud emas", false);
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalAttachment.isPresent())
            return new Result("Bunday fayl mavjud emas", false);
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalMeasurement.isPresent())
            return new Result("Bunday o'chov birligi mavjud emas", false);
        Product product = new Product();
        product.setName(productDto.getName());
        product.setCategory(optionalCategory.get());
        product.setPhoto(optionalAttachment.get());
        product.setMeasurement(optionalMeasurement.get());
        productRepository.save(product);
        return new Result("Ma'lumot muvaffaqiyatli o'gartirildi", true);
    }
}
