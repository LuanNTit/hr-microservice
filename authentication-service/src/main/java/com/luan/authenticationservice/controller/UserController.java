package com.luan.authenticationservice.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.luan.authenticationservice.dto.ResponseObject;
import com.luan.authenticationservice.dto.UserDTO;
import com.luan.authenticationservice.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "user-MG-services")
public class UserController {
    private final UserService userService;

	private final PasswordEncoder passwordEncoder;
    @GetMapping("/search")
    public ResponseEntity<ResponseObject> searchByName(@RequestParam String username) {
        List<UserDTO> userSearchByUsernames = userService.searchUser(username);
        return ResponseEntity.ok(new ResponseObject("ok", "List user search by user name successfully", userSearchByUsernames));
    }
    @PostMapping("")
    public ResponseEntity<ResponseObject> createUser(@RequestBody UserDTO user) {
    	user.setEncryptedPassword(passwordEncoder.encode(user.getEncryptedPassword()));
        UserDTO createdUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("ok", "User created successfully", createdUser));
    }
    
    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllUsers(@RequestParam(defaultValue = "1") int page,
                                                      @RequestParam(defaultValue = "5") int size,
                                                      @RequestParam(defaultValue = "userName") String sortField,
                                                      @RequestParam(defaultValue = "asc") String sortDirection) {
    	Page<UserDTO> pagingUsers = userService.getAllUsers(page, size, sortField, sortDirection);
        return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("ok", "List paging users successfully", pagingUsers));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ResponseObject> getUserById(@PathVariable Long userId) {
        try {
        	UserDTO user = userService.getUserById(userId);
        	return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", "Query employee successfully", user));
        } catch (Exception e) {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObject("failed", e.getMessage(), ""));
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ResponseObject> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        userDTO.setEncryptedPassword(passwordEncoder.encode(userDTO.getEncryptedPassword()));
        UserDTO updateUser = userService.updateUser(userId, userDTO);
        return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("ok", "Update Employee successfully", updateUser));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ResponseObject> deleteUser(@PathVariable Long userId) {
    	userService.deleteUserById(userId);
        return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("ok", "Delete product successfully", ""));
    }
}

