package com.cetpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cetpa.entities.Product;
import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Integer> {
	
List<Product> findByName(String name);
List<Product>findByBrand(String brand);
@Query("from Product where price<:arg")
List<Product>findByPriceLessThan(@Param("arg") int price);
@Query("from Product where price>:arg")
List<Product>findByPriceGreaterThan(@Param("arg") int price);
@Query("from Product where price between :arg1 and :arg2")
List<Product>findByPriceBetween(@Param("arg1") int price1,@Param("arg2") int price2);
}
