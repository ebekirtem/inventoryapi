package com.inventory.service;

import com.inventory.dto.category.CategoryDto;
import com.inventory.dto.category.CategoryMapper;
import com.inventory.dto.depot.DepotDto;
import com.inventory.dto.depot.DepotMapper;
import com.inventory.dto.product.*;
import com.inventory.dto.productdepot.CreateProductDepotDto;
import com.inventory.dto.productdepot.ProductDepotDto;
import com.inventory.dto.productdepot.ProductDepotMapper;
import com.inventory.exception.ResourceNotFoundException;
import com.inventory.model.Category;
import com.inventory.model.Depot;
import com.inventory.model.Product;
import com.inventory.model.ProductDepot;
import com.inventory.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final  DepotService depotService;
    private  final ProductDepotService productDepotService;

    private final CategoryMapper categoryMapper;
    private final DepotMapper depotMapper;

    private  final ProductMapper productMapper;

    private final ProductDepotMapper productDepotMapper;

    public ProductService(ProductRepository productRepository, CategoryService categoryService, DepotService depotService, ProductDepotService productDepotService, CategoryMapper categoryMapper, DepotMapper depotMapper, ProductMapper productMapper, ProductDepotMapper productDepotMapper) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.depotService = depotService;
        this.productDepotService = productDepotService;
        this.categoryMapper = categoryMapper;
        this.depotMapper = depotMapper;
        this.productMapper = productMapper;
        this.productDepotMapper = productDepotMapper;
    }

    @Transactional
    public ProductDto saveProduct(CreateProductDto createProductDto) {
         Integer categoryId= createProductDto.getCategoryId();
         Category category= categoryService.getCategory(categoryId);


        Product product =new Product();
        product.setName(createProductDto.getName());

        product.setCriticalThreshold(createProductDto.getCriticalThreshold());

        product.setCategory(category);

        Product savedProduct= productRepository.save(product);

        // to keep savedProductDepot

       return saveProductAndProductDepot(createProductDto,savedProduct,category);

    }

    private ProductDto saveProductAndProductDepot(CreateProductDto createProductDto, Product product,Category category ){
        List<ProductDepot> savedProductDepotList=new ArrayList<>();

        for (CreateProductDepotDto productDepotDto : createProductDto.getDepots()) {
            ProductDepot productDepot=new ProductDepot();
            productDepot.setProduct(product);

            Integer depotId= productDepotDto.getDepotId();
            Depot depot= depotService.getDepot(depotId);

            productDepot.setDepot(depot);
            productDepot.setQuantity(productDepotDto.getQuantity());
            ProductDepot savedProductDepot= productDepotService.saveProductDepot(productDepot);

            savedProductDepotList.add(savedProductDepot);
        }

        ProductDto productDto=new ProductDto();
        productDto.setName(product.getName());
        productDto.setId(product.getId());
        productDto.setCriticalThreshold(product.getCriticalThreshold());

        CategoryDto categoryDto = categoryMapper.categoryToCategoryDto(category);


        productDto.setCategory(categoryDto);


        List<ProductDepotDto> productDepotDtoList= savedProductDepotList.stream().map(pd->{
            ProductDepotDto productDepotDto=new ProductDepotDto();
            DepotDto depotDto= depotMapper.depotToDepotDto(pd.getDepot());
            productDepotDto.setDepot(depotDto);
            productDepotDto.setQuantity(pd.getQuantity());
            return productDepotDto;
        }).collect(Collectors.toList());


        productDto.setDepots(productDepotDtoList);

        return productDto;
    }
    @Transactional(readOnly = true)
    public Product getProduct(Long id){
       return productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Product not found with id"+id));
    }
    @Transactional
    public void deleteProduct(Long id){
        Product product= getProduct(id);
        productDepotService.deleteByProductId(id);
        productRepository.deleteById(product.getId());
    }

    @Transactional
    public void decreaseInStock(DecreaseStockDto decreaseStockDto){
        Product product= getProduct(decreaseStockDto.getProductId());
        Depot depot= depotService.getDepot(decreaseStockDto.getDepotId());
        productDepotService.decreaseStock(product,depot,decreaseStockDto.getQuantity(),product.getCriticalThreshold());
    }

    @Transactional
    public ProductDto updateProduct(Long id,CreateProductDto createProductDto){
         Product product= getProduct(id);
         Category category= categoryService.getCategory(createProductDto.getCategoryId());

         product.setName(createProductDto.getName());
         product.setCriticalThreshold(createProductDto.getCriticalThreshold());

         if(createProductDto.getCategoryId().intValue()!=product.getCategory().getId().intValue()){
             product.setCategory(category);
         }

        Product savedProduct= productRepository.save(product);

         productDepotService.deleteByProductId(product.getId());

        return saveProductAndProductDepot(createProductDto, savedProduct,category);
    }



    @Transactional(readOnly = true)
    public List<ProductDto> getAllByName(String name){
        List<Product> products= productRepository.findByName(name);

        return getProductDtos(products);
    }

    @Transactional(readOnly = true)
   public List<ProductDto> getAllByCategory(Integer categoryId){
        Category category=  categoryService.getCategory(categoryId);
        List<Product> products= productRepository.findByCategory(category);
        return getProductDtos(products);
    }

    @Transactional(readOnly = true)
    public List<ProductDto> getProductsByDepotName(String depotName){
        Depot depot=depotService.getDepotByName(depotName);
        return getProductDtos(depot);
    }

    @Transactional(readOnly = true)
    public List<ProductDto> getProductsByDepotId(Integer id){
        Depot depot=depotService.getDepot(id);
        return getProductDtos(depot);
    }


    @Transactional(readOnly = true)
    public Page<ProductDto> getProductDTOPage(Pageable pageable) {
        Page<Product> productPage= productRepository.findAll(pageable);
        Page<ProductDto> productDtoPage= productPage.map(new Function<Product, ProductDto>() {
            @Override
            public ProductDto apply(Product product) {
                return getProductDto(product);
            }
        });

        return productDtoPage;
    }

    private List<ProductDto> getProductDtos(List<Product> products) {
        return products.stream().map(p -> {
                    return getProductDto(p);
                }
        ).collect(Collectors.toList());
    }


    private ProductDto getProductDto(Product p) {
        ProductDto productDto = productMapper.productToProductDto(p);
        List<ProductDepot> pdList = productDepotService.findByProduct(p);
        List<ProductDepotDto> productDepotList = pdList.stream().map(pd -> productDepotMapper.productDepotToProductDepotDto(pd)).collect(Collectors.toList());
        productDto.setDepots(productDepotList);
        return productDto;
    }



    private List<ProductDto> getProductDtos(Depot depot) {
        List<ProductDepot> productDepots = productDepotService.findAllByDepot(depot);
        return productDepots.stream().map(pd -> {
                    return getProductDto(pd);
                }
        ).collect(Collectors.toList());
    }

    private ProductDto getProductDto(ProductDepot pd) {
        ProductDto productDto = productMapper.productToProductDto(pd.getProduct());
        ProductDepotDto productDepotDto = productDepotMapper.productDepotToProductDepotDto(pd);
        productDto.setDepots(Arrays.asList(productDepotDto));
        return productDto;
    }



}
