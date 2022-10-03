package uz.pdp.appwerehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwerehouse.entity.Product;
import uz.pdp.appwerehouse.payload.ProductDto;
import uz.pdp.appwerehouse.payload.Result;
import uz.pdp.appwerehouse.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    final
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // CREAT
    @PostMapping("/addProduct")
    public Result addProduct(@RequestBody ProductDto productDto){
        return productService.addProduct(productDto);
    }

    // GET ALL
    @GetMapping("/allProduct")
    public List<Product> allProduct(){
        return productService.allProduct();
    }

    // GET BY ID
    @GetMapping("/getProductById/{id}")
    public Product getProductById(@PathVariable Integer id){
        return productService.getProductById(id);
    }

    // DELETE BY ID
    @DeleteMapping("/deleteProductById/{id}")
    public Result deleteProductById(@PathVariable Integer id){
        return productService.deleteProductById(id);
    }

    // UPDATE BY ID
    @PutMapping("/editProductById/{id}")
    public Result editProductById(@PathVariable Integer id, @RequestBody ProductDto productDto){
        return productService.editProductById(id, productDto);
    }
}
