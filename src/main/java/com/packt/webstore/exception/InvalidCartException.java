package com.packt.webstore.exception;

public class InvalidCartException extends RuntimeException{
	private static final long serialVersionUID = -5192041563033358491L;
	private String cartId;
	public String getCartId() {
		return cartId;
	}
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public InvalidCartException(String cartId) {
		this.cartId = cartId;
	}
}
