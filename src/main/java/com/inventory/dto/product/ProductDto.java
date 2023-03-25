package com.inventory.dto.product;

import com.inventory.dto.category.CategoryDto;
import com.inventory.dto.productdepot.CreateProductDepotDto;
import com.inventory.dto.productdepot.ProductDepotDto;

import java.util.List;

public class ProductDto {
    private Long id;
    private String name;
    private CategoryDto category;

    private Integer criticalThreshold;

    private List<ProductDepotDto> depots;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }


    public List<ProductDepotDto> getDepots() {
        return depots;
    }

    public void setDepots(List<ProductDepotDto> depots) {
        this.depots = depots;
    }

    public Integer getCriticalThreshold() {
        return criticalThreshold;
    }

    public void setCriticalThreshold(Integer criticalThreshold) {
        this.criticalThreshold = criticalThreshold;
    }
}
