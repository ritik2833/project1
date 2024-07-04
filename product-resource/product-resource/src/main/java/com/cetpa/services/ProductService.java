package com.cetpa.services;

import java.util.List;

import com.cetpa.entities.Product;

public interface ProductService{
	void saveProduct(Product product);
	List<Product> getList();
	Product getDetailsById(int pid);
	List<Product> getListByName(String name);
	List<Product> getListByBrand(String brand);
	List<Product> getListByPriceLessThan(int price);
	List<Product> getListByPriceGreaterThan(int price);
	List<Product> getListByPriceBetween(int price1, int price2);
	Product getById(int pid);
	void deleteById(int pid);
	void updateProduct(Product product);
}