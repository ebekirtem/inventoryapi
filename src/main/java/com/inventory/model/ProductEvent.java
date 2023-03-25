package com.inventory.model;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "t_product_audit")
public class ProductEvent  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductEventType eventType;


    @Column(nullable = false)
    private Instant timestamp= Instant.now();

    private Long productId;

    private String productName;

    private Integer depotId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public ProductEventType getEventType() {
        return eventType;
    }

    public void setEventType(ProductEventType eventType) {
        this.eventType = eventType;
    }


    public Instant getTimestamp() {
        return timestamp;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getDepotId() {
        return depotId;
    }

    public void setDepotId(Integer depotId) {
        this.depotId = depotId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEvent that = (ProductEvent) o;
        return Objects.equals(id, that.id) && eventType == that.eventType && Objects.equals(timestamp, that.timestamp) && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eventType, timestamp, productId);
    }
}
