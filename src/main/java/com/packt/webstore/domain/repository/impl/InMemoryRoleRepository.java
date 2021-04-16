package com.packt.webstore.domain.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.packt.webstore.domain.Role;
import com.packt.webstore.domain.User;
import com.packt.webstore.domain.repository.RoleRepository;

@Repository
public class InMemoryRoleRepository implements RoleRepository {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<String> getByUsername(String username) {
		String sql = "SELECT * FROM Roles WHERE username = :name";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", username);
		return jdbcTemplate.query(sql, params, new RoleMapper());
	}
	
	private static final class RoleMapper implements RowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) 
				throws SQLException {
			Role role = Role.valueOf(rs.getString("role"));
			return role.label;
		}
	}
}