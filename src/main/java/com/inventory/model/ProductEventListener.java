package com.inventory.model;

import com.inventory.repository.ProductEventRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PreRemove;

@Component
public class ProductEventListener {
    private final ProductEventRepository productEventRepository;


    public ProductEventListener(@Lazy ProductEventRepository productEventRepository) {
        this.productEventRepository = productEventRepository;
    }
 
    @PostPersist
    public void onPostPersist(Product product) {
        createProductEvent(product, ProductEventType.ADDED);
    }
 
    @PostUpdate
    public void onPostUpdate(Product product) {
        createProductEvent(product, ProductEventType.UPDATED);
    }
 
    @PreRemove
    public void onPreRemove(Product product) {
        createProductEvent(product, ProductEventType.REMOVED);
    }
 
    private void createProductEvent(Product product, ProductEventType eventType) {
        ProductEvent productEvent = new ProductEvent();
        productEvent.setProductId(product.getId());
        productEvent.setProductName(product.getName());
        productEvent.setEventType(eventType);
        productEventRepository.save(productEvent);
    }
}
