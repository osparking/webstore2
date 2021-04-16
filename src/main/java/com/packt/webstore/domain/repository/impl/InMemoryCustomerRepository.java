package com.packt.webstore.domain.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.packt.webstore.domain.Address;
import com.packt.webstore.domain.Customer;
import com.packt.webstore.domain.repository.CustomerRepository;

@Repository
public class InMemoryCustomerRepository implements CustomerRepository {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<Customer> getAllCustomers() {
		Map<String, Object> params = new HashMap<String, Object>();
		List<Customer> result = jdbcTemplate.query(
				"SELECT * FROM customers", params, new CustomerMapper());

		return result;
	}

	@Override
	public List<Customer> getAllCustomers2() {
		Map<String, Object> params = new HashMap<String, Object>();
		String qry = "Select C.ID, C.name, C.phone_number,";

		qry += " A.ZIPCODE, A.WIDECIDO, A.CIGOONGU, A.STREETNAME,";
		qry += " A.BUILDINGNO, A.UNITNO ";
		qry += "From customers C";
		qry += " Join address A on C.billing_address_id = A.ID";

		List<Customer> result = jdbcTemplate.query(qry, params, new CustomerMapper2());

		return result;
	}

	private static final class CustomerMapper implements RowMapper<Customer> {
		@Override
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Customer customer = new Customer();
			customer.setCustomerId(rs.getString("ID"));
			customer.setName(rs.getString("NAME"));
			customer.setAddress(rs.getString("billing_address_id"));
			customer.setPhoneNumber(rs.getString("phone_number"));
			return customer;
		}
	}

	private static final class CustomerMapper2 implements RowMapper<Customer> {
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Customer customer = new Customer();
			customer.setCustomerIdLong(rs.getLong("ID"));
			customer.setName(rs.getString("NAME"));
			customer.setPhoneNumber(rs.getString("PHONE_NUMBER"));

			Address billAddress = new Address();
			billAddress.setZipCode(rs.getString("ZIPCODE"));
			billAddress.setWideCiDo(rs.getString("WIDECIDO"));
			billAddress.setCiGoonGu(rs.getString("CIGOONGU"));
			billAddress.setStreetName(rs.getString("STREETNAME"));
			billAddress.setBuildingNo(rs.getString("BUILDINGNO"));
			billAddress.setUnitNo(rs.getString("UNITNO"));
			customer.setBillingAddress(billAddress);
			return customer;
		}
	}

	@Override
	public void addCustomer(Customer customer) {
		String sql = "Insert into customers (ID, " + "cust_NAME, " + "address, " + "noOfOrdersMade) "
				+ "VALUES (:id, :name, :address, :noOfOrdersMade)";
		Map<String, Object> params = new HashMap<>();
		params.put("id", customer.getCustomerId());
		params.put("name", customer.getName());
		params.put("address", customer.getAddress());
		params.put("noOfOrdersMade", customer.getNoOfOrdersMade());
		jdbcTemplate.update(sql, params);
	}

	@Override
	public Customer getCustomer(String customerId) {
		String qry = "Select C.ID, C.name, C.phone_number,";

		qry += " A.ZIPCODE, A.WIDECIDO, A.CIGOONGU,	A.STREETNAME,";
		qry += " A.BUILDINGNO, A.UNITNO ";
		qry += "From customers C";
		qry += " Join address A on C.billing_address_id = A.ID ";
		qry += "WHERE C.ID = :id";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", customerId);
		Customer result = null;
		try {
			result = jdbcTemplate.queryForObject(qry, params, new CustomerMapper2());
		} catch (EmptyResultDataAccessException e) {
			if (result == null) {
				result = new Customer();
				result.setWrongId(true);
				result.setCustomerIdLong((long) Integer.parseInt(customerId));
			}
		}
		return result;
	}

	@Override
	public Boolean isCustomerExist(String customerId) {
		String sql = "SELECT count(*) FROM customers";
		sql += " WHERE ID = :id";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", customerId);

		int result = jdbcTemplate.queryForObject(sql, params, Integer.class);

		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}
}
