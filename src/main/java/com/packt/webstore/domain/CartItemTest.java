package com.packt.webstore.domain;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CartItemTest {
	private CartItem cartItem;
	
	@Before
	public void setup() {
		cartItem = new CartItem("1");
	}
	
	@Test
	public void cartItem_total_should_be_product() {
		// 시험할 자료 준비 - arrange
		Product iphone =
				new Product("P1 234","iPhone 5s", 500);
		cartItem.setProduct(iphone);
//		cartItem.setQuantity(1); // method 2
		
		// 코드 시험 개시 - act
		BigDecimal totalPrice = cartItem.getTotalPrice();
		
		// 결과값 비교 - assert
		Assert.assertEquals(iphone.getUnitPrice(), totalPrice);
	}
	
}
