package com.luan.authenticationservice.service;
import com.luan.authenticationservice.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
	List<UserDTO> getAllUsers();
	UserDTO saveUser(UserDTO user);
	UserDTO getUserById(Long id);
	void deleteUserById(Long id);
	UserDTO updateUser(Long id, UserDTO user);
	List<UserDTO> searchUser(String name);
	Page<UserDTO> getAllUsers(int page, int size, String sortField, String sortDirection);
}
