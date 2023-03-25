package com.inventory.service;

import com.inventory.dto.category.CategoryDto;
import com.inventory.dto.category.CategoryMapper;
import com.inventory.dto.category.CreateCategoryDto;
import com.inventory.exception.ResourceNotFoundException;
import com.inventory.model.Category;
import com.inventory.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository,CategoryMapper categoryMapper){
        this.categoryRepository=categoryRepository;
        this.categoryMapper=categoryMapper;
    }

    @Transactional
    public CategoryDto saveCategory(CreateCategoryDto createCategoryDto){
        Category category = categoryMapper.createCategoryDtoToCategory(createCategoryDto);
        Category savedCategory=categoryRepository.save(category);
        return categoryMapper.categoryToCategoryDto(savedCategory);
    }
    @Transactional(readOnly = true)
    public List<CategoryDto> getCategories(){
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category -> categoryMapper.categoryToCategoryDto(category)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Category getCategory(Integer id){
        Category category = categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category not found by id:"+id));
       return category;
    }
    @Transactional(readOnly = true)
    public CategoryDto getCategoryById(Integer id){
         Category category=getCategory(id);
         return categoryMapper.categoryToCategoryDto(category);
    }

    @Transactional
    public CategoryDto updateCategory(Integer id,CreateCategoryDto createCategoryDto){
        Category category = getCategory(id);
        category.setName(createCategoryDto.getName());
        Category updatedCategory=categoryRepository.save(category);
        return categoryMapper.categoryToCategoryDto(updatedCategory);
    }
}
