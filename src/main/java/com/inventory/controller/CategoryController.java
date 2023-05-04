package com.inventory.controller;

import com.inventory.dto.category.CategoryDto;
import com.inventory.dto.category.CreateCategoryDto;
import com.inventory.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryDto> saveCategory(@Valid @RequestBody CreateCategoryDto createCategoryDto){
        CategoryDto categoryDto = categoryService.saveCategory(createCategoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryDto);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAll(){
        List<CategoryDto> categories = categoryService.getCategories();
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer id){
        CategoryDto categoryDto = categoryService.getCategoryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(categoryDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CreateCategoryDto createCategoryDto,@PathVariable Integer id){
        CategoryDto categoryDto = categoryService.updateCategory(id,createCategoryDto);
        return ResponseEntity.status(HttpStatus.OK).body(categoryDto);
    }


}
