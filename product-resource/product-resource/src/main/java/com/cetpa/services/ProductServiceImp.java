package com.cetpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cetpa.entities.Product;
import com.cetpa.repositories.ProductRepository;

@Service
public class ProductServiceImp implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public void saveProduct(Product product) {
		// TODO Auto-generated method stub
		productRepository.save(product);
	}

	@Override
	public List<Product> getList() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	@Override
	public Product getDetailsById(int pid) {
		// TODO Auto-generated method stub
		return productRepository.findById(pid).orElse(null);
	}

	@Override
	public List<Product> getListByName(String name) {
		// TODO Auto-generated method stub
		return productRepository.findByName(name);
	}

	@Override
	public List<Product> getListByBrand(String brand) {
		// TODO Auto-generated method stub
		return productRepository.findByBrand(brand);
	}

	@Override
	public List<Product> getListByPriceLessThan(int price) {
		// TODO Auto-generated method stub
		return productRepository.findByPriceLessThan(price);
	}

	@Override
	public List<Product> getListByPriceGreaterThan(int price) {
		// TODO Auto-generated method stub
		return productRepository.findByPriceGreaterThan(price);
	}

	@Override
	public List<Product> getListByPriceBetween(int price1, int price2) {
		// TODO Auto-generated method stub
		return productRepository.findByPriceBetween(price1, price2);
	}

	@Override
	public Product getById(int pid) {
		// TODO Auto-generated method stub
		return productRepository.findById(pid).orElse(null);
	}

	@Override
	public void deleteById(int pid) {
		// TODO Auto-generated method stub
		productRepository.deleteById(pid);
	}

	@Override
	public void updateProduct(Product product) {
		// TODO Auto-generated method stub
		productRepository.save(product);
	}
	

}
