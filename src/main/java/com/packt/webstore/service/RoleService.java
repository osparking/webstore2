package com.packt.webstore.service;

import java.util.List;

import com.packt.webstore.domain.Role;

public interface RoleService {
	List<String> getByUsername(String username);
}
