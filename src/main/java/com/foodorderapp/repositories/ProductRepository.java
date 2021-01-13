package com.foodorderapp.repositories;

import com.foodorderapp.models.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("SELECT p FROM Product AS p WHERE p.available = true ")
    List<Product> findAll();

    Product findByName(String name);
}
