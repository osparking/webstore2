package com.packt.webstore.domain.repository;

import java.util.List;
import java.util.Map;

import com.packt.webstore.domain.Product;

public interface ProductRepository {
	List<Product> getAllProducts(String...args);
	void updateStock(String productId, long noOfUnits);
	List<Product> getByCategory(String category);
	List<Product> getByFilter(Map<String, List<String>> filter);
	Product getById(String productId);
	List<Product> listByCateSpec(String category, 
			Map<String, List<String>> specif, String brand);
	void addProduct(Product product);
}
