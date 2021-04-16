package com.packt.webstore.validator;
import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.packt.webstore.domain.Product;

@Component
public class UnitsInStockValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Product.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Product product = (Product)target;
		BigDecimal price = product.getUnitPrice();
		if (price != null &&
				new BigDecimal(1000000).compareTo(price) <= 0
				&& product.getUnitsInStock() >= 100) {
			errors.rejectValue("unitsInStock", 
					"com.packt.webstore.validator.UnitsInStockValidator.message");
		}
	}

}
