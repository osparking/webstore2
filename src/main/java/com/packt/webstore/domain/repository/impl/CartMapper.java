package com.packt.webstore.domain.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.packt.webstore.domain.Cart;
import com.packt.webstore.domain.CartItem;
import com.packt.webstore.service.ProductService;

public class CartMapper implements RowMapper<Cart> {
	private CartItemMapper cartItemMapper;
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public CartMapper(NamedParameterJdbcTemplate jdbcTemplate,
			ProductService productService) {
		this.jdbcTemplate = jdbcTemplate;
		cartItemMapper = new CartItemMapper(productService);
	}
	
	@Override
	public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
		String cartId = rs.getString("id");
		Cart cart = new Cart(cartId);
		String sql = String.format(
				"select * from cart_item where cart_id='%s'", cartId);
		List<CartItem> cartItems = jdbcTemplate.query(sql, cartItemMapper);
		cart.setCartItems(cartItems);
		return cart;
	}
}
