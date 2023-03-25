package com.inventory.service;

import com.inventory.exception.BadRequestException;
import com.inventory.exception.ResourceNotFoundException;
import com.inventory.model.Depot;
import com.inventory.model.Product;
import com.inventory.model.ProductDepot;
import com.inventory.repository.ProductDepotRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductDepotService {
    Logger logger= LoggerFactory.getLogger(ProductDepotService.class);
    private final ProductDepotRepository productDepotRepository;

    public ProductDepotService(ProductDepotRepository productDepotRepository) {
        this.productDepotRepository = productDepotRepository;
    }

    @Transactional
    public ProductDepot saveProductDepot(ProductDepot productDepot){
       return productDepotRepository.save(productDepot);
    }

    @Transactional(readOnly = true)
    public List<ProductDepot> findByProduct(Product product){
        return productDepotRepository.findProductDepotByProduct(product);
    }

    @Transactional(readOnly = true)
    public List<ProductDepot> findAllByDepot(Depot depot){
        return productDepotRepository.findAllByDepot(depot);
    }


    public void deleteByProductId(Long id){
        productDepotRepository.deleteByProduct(id);
    }

    @Transactional
    public void decreaseStock(Product product, Depot depot, Integer decreaseQuantity,Integer criticalThreshold){
        Optional<ProductDepot> productDepotOpt = productDepotRepository.findProductDepotByProductAndDepot(product, depot);
        if(productDepotOpt.isPresent()){
           ProductDepot productDepot= productDepotOpt.get();

           //check there is sufficient product ot not
           if(productDepot.getQuantity()<decreaseQuantity){
               throw new BadRequestException("There is no enough product in Stock");
           }

           //calculate new quantity in stock
           Integer newQuantity=productDepot.getQuantity()-decreaseQuantity;

           //logs if critical threshold is exceeded
           if(newQuantity<criticalThreshold){
                logger.warn("Exceeded Critical Threshold productId:{}, depotId:{} .......",product.getId(),depot.getId());
           }

           productDepot.setQuantity(newQuantity);
           productDepotRepository.save(productDepot);
        }else{
            throw new ResourceNotFoundException(String.format("ProductDepot not found with productId: %d and depotId: %d",product.getId(),depot.getId()));
        }
    }
}
