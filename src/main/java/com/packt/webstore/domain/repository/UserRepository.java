package com.packt.webstore.domain.repository;

import java.util.List;

import com.packt.webstore.domain.User;

public interface UserRepository {
	List<User> getAllUsers();
}
