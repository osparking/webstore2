package com.packt.webstore.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.packt.webstore.exception.ProductNotFoundException;
import com.packt.webstore.service.ProductService;

@Component
public class ProductIdValidator implements 
		ConstraintValidator<ProductId, String> {

	@Autowired
	private ProductService productService;

	@Override
	public void initialize(ProductId constraintAnnotation) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isValid(String productId, 
			ConstraintValidatorContext context) {
		try {
			productService.getById(productId);
		} catch (ProductNotFoundException e) {
			return true;
		}
		return false;
	}

}
