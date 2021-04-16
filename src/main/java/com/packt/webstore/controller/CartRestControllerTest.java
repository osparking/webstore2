package com.packt.webstore.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.packt.webstore.dto.CartDto;
import com.packt.webstore.dto.CartItemDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebApplicationContextConfig.class)
@WebAppConfiguration
public class CartRestControllerTest {
	@Autowired
	private WebApplicationContext wac;
	@Autowired
	MockHttpSession session;
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders
				.webAppContextSetup(this.wac)
				.build();
	}
	
	@Test
	public void update_method_should_return_200() throws Exception {
		// Arrange
		CartDto cart = new CartDto("C1236");
		this.mockMvc.perform(
				post("/rest/cart")
				.content(asJsonString(cart))
				.contentType(MediaType.APPLICATION_JSON)
				.session(session))
				.andExpect(status().isCreated());
		// Act
		CartItemDto cartItemDto = new CartItemDto();
		cartItemDto.setId("P4321");
		cartItemDto.setProductId("P1236");
		cart.addCartItem(cartItemDto);
		this.mockMvc.perform(put("/rest/cart/C1236" 
				).content(asJsonString(cart))
				.contentType(MediaType.APPLICATION_JSON)
				.session(session)).andExpect(status().isOk());
	}
	
	@Test public void update_return_200() throws Exception { 
		// Arrange 
		CartDto cart = new CartDto("C1237");
		
		CartItemDto cartItemDto = new CartItemDto();
		cartItemDto.setId("Ci_1235"); 
		cartItemDto.setProductId("P1235"); //
		cart.addCartItem(cartItemDto);
  
		RequestBuilder req = post("/rest/cart").content(asJsonString(cart))
				.contentType(MediaType.APPLICATION_JSON).session(session);
  
		this.mockMvc.perform(req).andExpect(status().isCreated());
  
		// Act 
		cartItemDto = new CartItemDto();
		cartItemDto.setId("Ci_2000"); 
		cartItemDto.setProductId("P1236");
		cart.addCartItem(cartItemDto);
  
		req = put("/rest/cart/C1237")
				.content(asJsonString(cart))
				.contentType(MediaType.APPLICATION_JSON)
				.session(session);
  
		this.mockMvc.perform(req).andExpect(status().isOk());
		req = get("/rest/cart/C1237"); 
		this.mockMvc.perform(req) 
				.andExpect(jsonPath("$.cartItems[0].product.productId")
						.value("P1235"))
				.andExpect(jsonPath("$.cartItems[1].product.productId")
						.value("P1236"));
	}
	
	@Test public void update_return_200_session() throws Exception { 
		// Arrange 
		CartDto cart = new CartDto(session.getId());
		
		CartItemDto cartItemDto = new CartItemDto();
		cartItemDto.setId("Ci_1235"); 
		cartItemDto.setProductId("P1235"); //
		cart.addCartItem(cartItemDto);
		
		RequestBuilder req = post("/rest/cart").content(asJsonString(cart))
				.contentType(MediaType.APPLICATION_JSON).session(session);
		
		this.mockMvc.perform(req).andExpect(status().isCreated());
		
		// Act 
		cartItemDto = new CartItemDto();
		cartItemDto.setId("Ci_2000"); 
		cartItemDto.setProductId("P1236");
		cart.addCartItem(cartItemDto);
		
		req = put("/rest/cart/" + session.getId())
				.content(asJsonString(cart))
				.contentType(MediaType.APPLICATION_JSON)
				.session(session);
		
		this.mockMvc.perform(req).andExpect(status().isOk());
		req = get("/rest/cart/" + session.getId()); 
		this.mockMvc.perform(req) 
		.andExpect(jsonPath("$.cartItems[0].product.productId")
				.value("P1235"))
		.andExpect(jsonPath("$.cartItems[1].product.productId")
				.value("P1236"));
	}
	
	@Test
	public void create_cart_should_create_one() throws Exception {
		// Arrange
		CartDto cart = new CartDto("Cart321");
		RequestBuilder req = post("/rest/cart")
				.content(asJsonString(cart))
				.contentType(MediaType.APPLICATION_JSON)
				.session(session);
		this.mockMvc.perform(req).andExpect(status().isCreated());
	}

	private static String asJsonString(CartDto cart) {
		final ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		
		try {
			jsonString = mapper.writeValueAsString(cart);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return jsonString;
	}

	@Test
	public void read_method_should_return_correct_cart_Json_object() throws Exception {
		// Arrange
		this.mockMvc.perform(put("/rest/cart/add/P1234").session(session)).andExpect(status().is(200));
		// Act
		this.mockMvc.perform(get("/rest/cart/" + session.getId())
				.session(session)).andExpect(status().isOk())
				.andExpect(
						jsonPath("$.cartItems[0].product.productId")
						.value("P1234"));
	}	
	
	@Test
	public void addItem_return_200() throws Exception {
		// Act
		RequestBuilder request = 
				put("/rest/cart/add/P1234").session(session);
		this.mockMvc.perform(request).andExpect(status().is(200));
	}
	
	@Test 
	public void remove_return_ok() throws Exception {
		// Arrange
		RequestBuilder request = 
				put("/rest/cart/add/P1234").session(session);
		this.mockMvc.perform(request);
		// Act
		request = 
				put("/rest/cart/remove/P1234").session(session);
		this.mockMvc.perform(request).andExpect(status().isOk());	
	}
	
	@Test 
	public void delete_return_ok() throws Exception {
		// Arrange
		RequestBuilder request = 
				put("/rest/cart/add/P1234").session(session);
		this.mockMvc.perform(request);
		// Act
		request = delete("/rest/cart/delete/" + session.getId());
		this.mockMvc.perform(request).andExpect(status().isOk());	
	}
}
