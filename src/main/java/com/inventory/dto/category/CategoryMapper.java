package com.inventory.dto.category;


import com.inventory.model.Category;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto categoryToCategoryDto(Category category);
    Category categoryDtoToCategory(CategoryDto categoryDto);

    Category createCategoryDtoToCategory(CreateCategoryDto createCategoryDto);


}

