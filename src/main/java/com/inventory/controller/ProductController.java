package com.inventory.controller;

import com.inventory.dto.depot.DepotDto;
import com.inventory.dto.product.CreateProductDto;
import com.inventory.dto.product.DecreaseStockDto;
import com.inventory.dto.product.ProductDto;
import com.inventory.dto.product.ProductUpdateDto;
import com.inventory.model.Product;
import com.inventory.model.ProductDepot;
import com.inventory.service.ProductDepotService;
import com.inventory.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    private final ProductDepotService productDepotService;

    public ProductController(ProductService productService, ProductDepotService productDepotService) {
        this.productService = productService;
        this.productDepotService = productDepotService;
    }

    @PostMapping
    public ResponseEntity<ProductDto> saveProduct (@Valid @RequestBody CreateProductDto createProductDto){
       ProductDto productDto= productService.saveProduct(createProductDto);

       return ResponseEntity.status(HttpStatus.CREATED).body(productDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct (@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/pages")

    public ResponseEntity<Page<ProductDto>> getProductByPage(@RequestParam("page") int page, @RequestParam("size") int size,
                                                             @RequestParam("sort") String prop,
                                                             @RequestParam(value="direction",required=false,defaultValue="DESC") Sort.Direction direction){

        Pageable pageable= PageRequest.of(page, size,Sort.by(direction,prop));
        Page<ProductDto> productDtoPage = productService.getProductDTOPage(pageable);
        return ResponseEntity.ok(productDtoPage);
    }

    /**
     * This method is used to remove an amount of product from stock
     * @param decreaseStockDto
     * @return
     */
    @PutMapping("/decrease")
    public ResponseEntity<Void> decreaseProductInStock(@RequestBody DecreaseStockDto decreaseStockDto){
        productService.decreaseInStock(decreaseStockDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @Valid @RequestBody CreateProductDto createProductDto){
        ProductDto productDto= productService.updateProduct(id,createProductDto);
        return ResponseEntity.status(HttpStatus.OK).body(productDto);
    }


    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllByName(@RequestParam String name){
        List<ProductDto> products= productService.getAllByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }


    @GetMapping("/category/{id}")
    public ResponseEntity<List<ProductDto>> getAllByCategoryId(@PathVariable Integer id){
        List<ProductDto> products= productService.getAllByCategory(id);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/depot")
    public ResponseEntity<List<ProductDto>> getAllByDepotName(@RequestParam String name){
        List<ProductDto> products= productService.getProductsByDepotName(name);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/depot/{id}")
    public ResponseEntity<List<ProductDto>> getAllByDepotId(@PathVariable Integer id){
        List<ProductDto> products= productService.getProductsByDepotId(id);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

}
