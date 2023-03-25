package com.inventory.dto.product;


import com.inventory.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

   ProductDto productToProductDto(Product product);

}

