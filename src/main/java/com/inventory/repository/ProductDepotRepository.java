package com.inventory.repository;

import com.inventory.model.Depot;
import com.inventory.model.Product;
import com.inventory.model.ProductDepot;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDepotRepository extends JpaRepository<ProductDepot,Long> {

   Optional <ProductDepot> findProductDepotByProductAndDepot(Product product, Depot depot);

   List<ProductDepot> findProductDepotByProduct(Product product);

   @Modifying
   @Query("delete from ProductDepot pd where pd.product.id=:id")
   void deleteByProduct(@Param("id") Long id);
   @EntityGraph(attributePaths = {"product.category","depot"})
   List<ProductDepot> findAllByDepot(Depot depot);
}
