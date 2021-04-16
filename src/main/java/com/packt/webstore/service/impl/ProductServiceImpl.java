package com.packt.webstore.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.ProductRepository;
import com.packt.webstore.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Override
	public void updateAllStock() {
		List<Product> allProducts = productRepository.getAllProducts();
		for (Product product : allProducts) {
			if (product.getUnitsInStock() < 500) {
				productRepository.updateStock(product.getProductId(), 
						product.getUnitsInStock() + 1000);
			}
		}
	}

	@Override
	public List<Product> getAllProducts(String...args) {
		return productRepository.getAllProducts(args);
	}

	@Override
	public List<Product> getByCategory(String category) {
		return productRepository.getByCategory(category);
	}

	@Override
	public List<Product> getByFilter(Map<String, List<String>> filter) {
		return productRepository.getByFilter(filter);
	}

	@Override
	public Product getById(String productId) {
		return productRepository.getById(productId);
	}

	@Override
	public List<Product> listByCateSpec(String category, 
			Map<String, List<String>> specif, String brand) {
		return productRepository.listByCateSpec(category, specif, brand);
	}

	@Override
	public void addProduct(Product product) {
		productRepository.addProduct(product);
	}
}