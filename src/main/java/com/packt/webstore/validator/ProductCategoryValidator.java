package com.packt.webstore.validator;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProductCategoryValidator implements ConstraintValidator<ProductCategory, String> {
	List<String> categories = new ArrayList<String>();

	@Override
	public void initialize(ProductCategory constraintAnnotation) {
		categories.add("Smartphone");
		categories.add("Laptop");
		categories.add("Desktop");
		categories.add("Tablet");
	}

	@Override
	public boolean isValid(String category, ConstraintValidatorContext context) {
		if (categories.contains(category))
			return true;
		else
			return false;
	}

}
