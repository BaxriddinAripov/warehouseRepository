package uz.pdp.appwerehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwerehouse.entity.Category;
import uz.pdp.appwerehouse.payload.CategoryDto;
import uz.pdp.appwerehouse.payload.Result;
import uz.pdp.appwerehouse.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    final
    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // CREAT
    @PostMapping("/addCategory")
    public Result addCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.addCategory(categoryDto);
    }

    // GET ALL
    @GetMapping("/allCategory")
    public List<Category> allCategory(){
        return categoryService.allCategory();
    }

    // GET BY ID
    @GetMapping("/getCategoryById/{id}")
    public Category getCategoryById(@PathVariable Integer id){
        return categoryService.getCategoryById(id);
    }

    // DELETE BY ID
    @DeleteMapping("/deleteCategoryById/{id}")
    public Result deleteCategoryById(@PathVariable Integer id){
        return categoryService.deleteCategoryById(id);
    }

    // UPDATE BY ID
    @PutMapping("/editCategoryById/{id}")
    public Result editCategoryById(@PathVariable Integer id, @RequestBody CategoryDto categoryDto){
        return categoryService.editCategoryById(id, categoryDto);
    }
}
