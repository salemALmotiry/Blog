package com.example.blog.Service;

import com.example.blog.ApiResponse.ApiException;
import com.example.blog.Model.Category;
import com.example.blog.Repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    public void updateCategory(Integer categoryId, Category category) {
        Category oldCategory = categoryRepository.findCategoryByCategoryId(categoryId);
        if (oldCategory == null) {
            throw new ApiException("Category not found");
        }
        oldCategory.setName(category.getName());
        categoryRepository.save(oldCategory);
    }

    public void deleteCategory(Integer categoryId) {
        Category oldCategory = categoryRepository.findCategoryByCategoryId(categoryId);
        if (oldCategory == null) {
            throw new ApiException("Category not found");
        }
        categoryRepository.delete(oldCategory);
    }

    //______________________________________________________________

    public Category getCategoryById(Integer categoryId) {
        Category category = categoryRepository.findCategoryByCategoryId(categoryId);
        if (category == null) {
            throw new ApiException("Category not found");
        }
        return category;
    }
}
