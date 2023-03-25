package com.inventory.repository;

import com.inventory.model.Depot;
import jdk.nashorn.internal.runtime.regexp.joni.encoding.ObjPtr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepotRepository extends JpaRepository<Depot,Integer> {
   public Optional<Depot> findByName(String name);
}
