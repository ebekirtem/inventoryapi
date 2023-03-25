package com.inventory.repository;

import com.inventory.model.ProductEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductEventRepository extends JpaRepository<ProductEvent,Long>{
}
