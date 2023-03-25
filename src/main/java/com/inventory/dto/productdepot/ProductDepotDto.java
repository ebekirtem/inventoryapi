package com.inventory.dto.productdepot;

import com.inventory.dto.depot.DepotDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ProductDepotDto {

    private DepotDto depot;

    private Integer quantity;


    public DepotDto getDepot() {
        return depot;
    }

    public void setDepot(DepotDto depot) {
        this.depot = depot;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
