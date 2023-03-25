package com.inventory.dto.category;



import com.inventory.dto.ApiRequestDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreateCategoryDto extends ApiRequestDto {
    @NotBlank(message = "Please Provide a Name")
    @Size(min=1, max=100,message = "Name must be 1 and 100 char long")
    private String name;

    public CreateCategoryDto(){

    }

    public CreateCategoryDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
