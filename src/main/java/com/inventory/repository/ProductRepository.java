package com.inventory.repository;

import com.inventory.model.Category;
import com.inventory.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByCategory(Category category);
    List<Product> findByName(String name);

    @EntityGraph(attributePaths = "category")
    Page<Product> findAll(Pageable pageable);
}
