package com.inventory.dto.product;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class DecreaseStockDto {
    @NotNull(message = "Please provide a ProductId")
    private Long productId;

    @NotNull(message = "Please provide a DepotId")
    private Integer depotId;

    @NotNull (message = "Please provide a quantity")
    @Positive(message = "quantity must be positive")
    private Integer quantity;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getDepotId() {
        return depotId;
    }

    public void setDepotId(Integer depotId) {
        this.depotId = depotId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
