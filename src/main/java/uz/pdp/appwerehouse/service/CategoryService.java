package uz.pdp.appwerehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwerehouse.entity.Category;
import uz.pdp.appwerehouse.payload.CategoryDto;
import uz.pdp.appwerehouse.payload.Result;
import uz.pdp.appwerehouse.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    final
    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // CREAT
    public Result addCategory(CategoryDto categoryDto) {
        boolean exists = categoryRepository.existsByName(categoryDto.getName());
        if (exists) {
            return new Result("Bunday kategoriya mavjud", false);
        }
        Category category = new Category();
        category.setName(categoryDto.getName());
        if (categoryDto.getParentCategoryId() != null) {
            Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (!optionalCategory.isPresent())
                return new Result("Bunday ota kategoriya mavjud emas", false);
            category.setParentCategory(optionalCategory.get());
        }
        categoryRepository.save(category);
        return new Result("Muvaffaqiyatli saqlandi", true);
    }

    // GET ALL
    public List<Category> allCategory() {
        return categoryRepository.findAll();
    }

    // GET BY ID
    public Category getCategoryById(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElse(null);
    }

    // DELETE BY UI
    public Result deleteCategoryById(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent())
            return new Result("Bunday kategoriya mavjud emas", false);
        categoryRepository.deleteById(id);
        return new Result("Malumot muvaffaqiyatli o'chirildi", true);
    }

    // UPDATE BY ID
    public Result editCategoryById(Integer id, CategoryDto categoryDto) {
        boolean exists = categoryRepository.existsByName(categoryDto.getName());
        if (exists) {
            return new Result("Bunday nomli kategoriya mavjud", false);
        }
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent())
            return new Result("Bunday kategoriya mavjud", false);
        Category category = optionalCategory.get();
        category.setName(categoryDto.getName());
        if (categoryDto.getParentCategoryId() != null) {
            Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (!optionalParentCategory.isPresent())
                return new Result("Bunday ota kategoriya mavjud emas", false);
            category.setParentCategory(optionalParentCategory.get());
        }
        categoryRepository.save(category);
        return new Result("Muvaffaqiyatli saqlandi", true);
    }
}
