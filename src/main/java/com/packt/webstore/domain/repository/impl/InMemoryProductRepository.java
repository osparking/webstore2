package com.packt.webstore.domain.repository.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.ProductRepository;
import com.packt.webstore.exception.CategoryNoProduct;
import com.packt.webstore.exception.ProductNotFoundException;

@Repository
public class InMemoryProductRepository implements ProductRepository {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public void updateStock(String productId, long noOfUnits) {
		String SQL = "UPDATE PRODUCTS SET " + "UNITS_IN_STOCK = :unitsInStock WHERE ID = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("unitsInStock", noOfUnits);
		params.put("id", productId);
		jdbcTemplate.update(SQL, params);
	}

	private static final class ProductMapper implements RowMapper<Product> {
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product product = new Product();
			product.setProductId(rs.getString("ID"));
			product.setName(rs.getString("PROD_NAME"));
			product.setDescription(rs.getString("DESCRIPTION"));
			BigDecimal unitPrice = rs.getBigDecimal("UNIT_PRICE");
			product.setUnitPrice(unitPrice);
			String unitPriceComma = String.format("%,.0f", unitPrice.setScale(0, RoundingMode.DOWN));
			product.setUnitPriceComma(unitPriceComma);
			product.setManufacturer(rs.getString("MANUFACTURER"));
			product.setCategory(rs.getString("CATEGORY"));
			product.setCondition(rs.getString("PROD_CONDITION"));
			long unitsInStock = rs.getLong("UNITS_IN_STOCK");
			product.setUnitsInStock(unitsInStock);
			String unitsInStockComma = String.format("%,d", unitsInStock);
			product.setUnitsInStockComma(unitsInStockComma);
			product.setUnitsInOrder(rs.getLong("UNITS_IN_ORDER"));
			product.setDiscontinued(rs.getBoolean("DISCONTINUED"));
			return product;
		}
	}

	@Override
	public List<Product> getAllProducts(String... args) {
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "SELECT * FROM products";

		if (args.length == 1) {
			sql += " WHERE LCASE(CATEGORY) = :category";
			params.put("category", args[0]);
		}
		List<Product> result = jdbcTemplate.query(sql, params, new ProductMapper());
		return result;
	}

	@Override
	public List<Product> getByCategory(String category) {
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "SELECT * FROM products WHERE " + "LCASE(CATEGORY) = :category";
		params.put("category", category.toLowerCase());
		List<Product> result = 
					jdbcTemplate.query(sql, params, new ProductMapper());
		if (result.size() == 0) {
			throw new CategoryNoProduct(category);
		} else {
			return result;
		}
	}

	@Override
	public List<Product> getByFilter(Map<String, List<String>> filter) {
		String sql = "SELECT * FROM PRODUCTS WHERE " 
					+ "CATEGORY IN (:categories) AND " + "MANUFACTURER IN (:brands)";

		return jdbcTemplate.query(sql, filter, new ProductMapper());
	}

	@Override
	public Product getById(String productId) {
		String sql = "SELECT * FROM products WHERE id=:id";
		Map<String, Object> params = new HashMap<>();
		params.put("id", productId);
		Product p = null;
		try {
			p = jdbcTemplate.queryForObject(sql, params, new ProductMapper());
			p.getUnitPriceComma();
		} catch (DataAccessException de) {
			throw new ProductNotFoundException(productId);
		}
		return p;
	}

	@Override
	public List<Product> listByCateSpec(String category, 
			Map<String, List<String>> specif, String brand) {
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "SELECT * FROM products WHERE " 
				+ "LCASE(CATEGORY) = LCASE(:category) and "
				+ "unit_price >= :low and "
				+ "unit_price <= :high and "
				+ "LCASE(manufacturer) like lcase(:brand) ";
		params.put("category", category.toLowerCase());
		params.put("low", specif.get("low"));
		params.put("high", specif.get("high"));
		params.put("brand", brand);
		return jdbcTemplate.query(sql, params, new ProductMapper());
	}

	@Override
	public void addProduct(Product product) {
		String sql = "Insert into Products (ID, " 
				+ "PROD_NAME, "  
				+ "DESCRIPTION, "  
				+ "UNIT_PRICE, "  
				+ "MANUFACTURER, "  
				+ "CATEGORY, "  
				+ "PROD_CONDITION, "  
				+ "UNITS_IN_STOCK, "
				+ "UNITS_IN_ORDER, "
				+ "DISCONTINUED) "
				+ "VALUES (:id, :name, :desc, :price, "
				+ ":manufacturer, :category, :condition, "
				+ ":inStock, :inOrder, :discontinued)";
		Map<String, Object> params = new HashMap<>();
		params.put("id", product.getProductId());
		params.put("name", product.getName());
		params.put("desc", product.getDescription());
		params.put("price", product.getUnitPrice());
		params.put("manufacturer", product.getManufacturer());
		params.put("category", product.getCategory());
		params.put("condition", product.getCondition());
		params.put("inStock", product.getUnitsInStock());
		params.put("inOrder", product.getUnitsInOrder());
		params.put("discontinued", product.isDiscontinued());
		jdbcTemplate.update(sql, params);
	}
}








