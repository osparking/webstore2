package com.packt.webstore.exception;

public class CategoryNoProduct extends RuntimeException {

	private static final long serialVersionUID = -6006381851090481903L;
	private String category;
	public CategoryNoProduct(String category) {
		this.category = category;
	}
	public String getCategory() {
		return category;
	}	
}
