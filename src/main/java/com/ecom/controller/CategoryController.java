package com.ecom.controller;

import com.ecom.config.AppConstants;
import com.ecom.payload.CategoryDTO;
import com.ecom.payload.CategoryResponse;
import com.ecom.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CategoryController {
    CategoryService CategoryServiceImpl;

    public CategoryController(CategoryService categoryServiceImpl) {
        CategoryServiceImpl = categoryServiceImpl;
    }

    @GetMapping("/public/categories")
    public ResponseEntity<CategoryResponse> getAllCategories(@RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                             @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                             @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_CATEGORIES_BY, required = false) String sortBy,
                                                             @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder) {

        CategoryResponse allCategories = CategoryServiceImpl.getAllCategories(pageNumber, pageSize, sortBy, sortOrder);
        return ResponseEntity.ok(allCategories);
    }

    @PostMapping(value = "/public/categories")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO createdCategory = CategoryServiceImpl.createCategory(categoryDTO);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategoryById(@PathVariable Long categoryId) {
        CategoryDTO deletedCategory = CategoryServiceImpl.deleteCategoryById(categoryId);
        return ResponseEntity.ok(deletedCategory);
    }

    @PutMapping(value = "/public/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Long categoryId) {
        CategoryDTO updatedCategory = CategoryServiceImpl.updateCategory(categoryDTO, categoryId);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedCategory);

    }
}

