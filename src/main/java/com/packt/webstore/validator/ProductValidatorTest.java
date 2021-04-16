package com.packt.webstore.validator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;

import com.packt.webstore.controller.WebApplicationContextConfig;
import com.packt.webstore.domain.Product;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebApplicationContextConfig.class)
@WebAppConfiguration

public class ProductValidatorTest {
	@Autowired
	private ProductValidator productValidator;

	@Test
	public void product_without_UnitPrice_should_be_invalid() {
		// Arrange
		Product product = new Product();
		BindException bindException = new BindException(product, "product");
		// Act
		ValidationUtils.invokeValidator(productValidator, product, bindException);
		// Assert
		Assert.assertEquals(3, bindException.getErrorCount());
		String result = bindException.getLocalizedMessage();
		Assert.assertTrue(bindException.getLocalizedMessage()
				.contains("���� ���� ����. ���� �ԷµǾ�� ��"));
	}
	
	@Test
	public void product_with_existing_productId_invalid() {
		// Arrange
		Product product = new Product("P1234", "iPhone 5s", 500);
		product.setCategory("Tablet");
		BindException bindException = new BindException(product, "product");
		// Act
		ValidationUtils.invokeValidator(productValidator, product, bindException);
		// Assert
		Assert.assertEquals(1, bindException.getErrorCount());
		String resultString = bindException.getLocalizedMessage();
		Assert.assertTrue(bindException.getLocalizedMessage().contains("���� ID�� ������ ��ǰ�� ������"));
	}

	@Test
	public void a_valid_product_should_not_get_any_error_during_validation() {
		// Arrange
		Product product = new Product("P9876", "iPhone 5s", 500);
		product.setCategory("Tablet");
		BindException bindException = new BindException(product, "product");
		// Act
		ValidationUtils.invokeValidator(productValidator, product, bindException);
		// Assert
		Assert.assertEquals(0, bindException.getErrorCount());
	}	
}
