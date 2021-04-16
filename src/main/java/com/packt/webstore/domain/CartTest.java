package com.packt.webstore.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class CartTest {
	private Cart cart;
	
	@Before
	public void setup() {
		cart = new Cart("C1");
	}
	
	@Test
	public void cart_get_grand_total() {
		BigDecimal expectedTotal = BigDecimal.ZERO;
		
		List<CartItem> cartItems = new ArrayList<>();
		CartItem cItem = new CartItem("1");
		Product prod = new Product("P1234","iPhone 5s", 500);
		
		cItem.setProduct(prod);
		cartItems.add(cItem);	
		expectedTotal = expectedTotal.add(cItem.getTotalPrice());
		
		cItem = new CartItem("2");
		prod = new Product("P1235","Gelaxy 20", 1000);
		
		cItem.setProduct(prod);
		cartItems.add(cItem);
		expectedTotal = expectedTotal.add(cItem.getTotalPrice());
		
		cart.setCartItems(cartItems);
		
		Assert.assertEquals(expectedTotal.longValue(), 
				cart.getGrandTotal().longValue());
	}
}
