package com.inventory.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * it is class that keeps product Id, depot id and product quantity in each depot
 */
@Entity
@Table(name="t_product_depot")
@EntityListeners(ProductDepotEventListener.class)
public class ProductDepot extends BaseEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @ManyToOne
   @JoinColumn(name = "product_id")
   private Product product;

   @ManyToOne
   @JoinColumn(name = "depot_id")
   private Depot depot;

   private Integer quantity;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Product getProduct() {
      return product;
   }

   public void setProduct(Product product) {
      this.product = product;
   }

   public Depot getDepot() {
      return depot;
   }

   public void setDepot(Depot depot) {
      this.depot = depot;
   }

   public Integer getQuantity() {
      return quantity;
   }

   public void setQuantity(Integer quantity) {
      this.quantity = quantity;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      ProductDepot that = (ProductDepot) o;
      return Objects.equals(id, that.id) && Objects.equals(product, that.product) && Objects.equals(depot, that.depot);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, product, depot);
   }
}