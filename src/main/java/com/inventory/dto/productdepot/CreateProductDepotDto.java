package com.inventory.dto.productdepot;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CreateProductDepotDto {
    @NotNull (message = "Please provide a depot Id")
    private Integer depotId;

    @NotNull(message = "Please provide a quantity")
    @Positive(message = "Quantity must be positive")
    private Integer quantity;

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
