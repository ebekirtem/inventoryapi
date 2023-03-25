package com.inventory.dto.product;

import com.inventory.dto.category.CategoryDto;
import com.inventory.dto.productdepot.CreateProductDepotDto;
import com.inventory.dto.productdepot.ProductDepotDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

public class ProductUpdateDto {
    @NotBlank(message = "Please Provide a Name")
    @Size(min=1, max=100,message = "Name must be 1 and 100 char long")
    private String name;

    @NotNull(message = "Please provide a category Id")
    private Integer categoryId;

    private List<CreateProductDepotDto> depots;

    @NotNull (message = "Please provide a criticalThreshold")
    @Positive(message = "CriticalThreshold must be positive")
    private Integer criticalThreshold;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public List<CreateProductDepotDto> getDepots() {
        return depots;
    }

    public void setDepots(List<CreateProductDepotDto> depots) {
        this.depots = depots;
    }


    public Integer getCriticalThreshold() {
        return criticalThreshold;
    }

    public void setCriticalThreshold(Integer criticalThreshold) {
        this.criticalThreshold = criticalThreshold;
    }
}
