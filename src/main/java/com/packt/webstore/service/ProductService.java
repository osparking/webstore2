package com.packt.webstore.service;

import java.util.List;
import java.util.Map;

import com.packt.webstore.domain.Customer;
import com.packt.webstore.domain.Product;

public interface ProductService {
	void updateAllStock();
	List<Product> getAllProducts(String...args);
	List<Product> getByCategory(String category);
	List<Product> getByFilter(Map<String, List<String>> filter);
	Product getById(String productId);
	List<Product> listByCateSpec(String category, 
			Map<String, List<String>> specif, String brand);
	void addProduct(Product product);
}
