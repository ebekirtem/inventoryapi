package com.inventory.model;

import com.inventory.repository.ProductEventRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PreRemove;

@Component
public class ProductDepotEventListener {
    private final ProductEventRepository productEventRepository;


    public ProductDepotEventListener(@Lazy ProductEventRepository productEventRepository) {
        this.productEventRepository = productEventRepository;
    }
 

    @PostUpdate
    public void onPostUpdate(ProductDepot product) {
        createProductDepotEvent(product, ProductEventType.OUT);
    }

    private void createProductDepotEvent(ProductDepot productDepot, ProductEventType eventType) {
        ProductEvent productEvent = new ProductEvent();
        productEvent.setProductId(productDepot.getProduct().getId());
        productEvent.setProductName(productDepot.getProduct().getName());
        productEvent.setEventType(eventType);
        productEvent.setDepotId(productDepot.getDepot().getId());
        productEventRepository.save(productEvent);
    }
}
