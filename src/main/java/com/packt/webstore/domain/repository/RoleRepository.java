package com.packt.webstore.domain.repository;

import java.util.List;

import com.packt.webstore.domain.Role;

public interface RoleRepository {
	List<String> getByUsername(String username);
}
