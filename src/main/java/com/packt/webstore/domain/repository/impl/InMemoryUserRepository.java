package com.packt.webstore.domain.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.packt.webstore.domain.Role;
import com.packt.webstore.domain.User;
import com.packt.webstore.domain.repository.UserRepository;
import com.packt.webstore.service.RoleService;
import com.packt.webstore.service.impl.RoleServiceImpl;

@Repository
public class InMemoryUserRepository implements UserRepository {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	private RoleService roleService;
	
	@Override
	public List<User> getAllUsers() {
		String sql = "SELECT * FROM users";
		List<User> users = jdbcTemplate.query(sql, new UserMapper());
		
		for (User u: users) {
			List<String> roles = roleService.getByUsername(u.getUsername());
			u.setRoles(roles);
		}
		return users;
	}
	
	private static final class UserMapper implements RowMapper<User> {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			
			user.setUsername(rs.getString("username"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			Timestamp ts = rs.getTimestamp("create_time");
			user.setCreate_time(ts.toLocalDateTime());

			return user;
		}
	}	

}
