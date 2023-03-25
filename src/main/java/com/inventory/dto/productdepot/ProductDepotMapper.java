package com.inventory.dto.productdepot;


import com.inventory.dto.product.ProductDto;
import com.inventory.model.Product;
import com.inventory.model.ProductDepot;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductDepotMapper {

   ProductDepotDto productDepotToProductDepotDto(ProductDepot productDepot);

}

