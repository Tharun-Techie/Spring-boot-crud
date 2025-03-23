package com.tharun.spring_boot_crud.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tharun.spring_boot_crud.entity.UserEntity;
import com.tharun.spring_boot_crud.exceptions.ResourceNotFoundException;
import com.tharun.spring_boot_crud.repository.UserRepository;

@RestController
@RequestMapping("api/users")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public List<UserEntity> getUsers() {
//		return Arrays.asList(new User(1L,"Tharun","Tharuntechie@gmail.com"),
//							 new User(2L,"Rajan","Rajan@gmail.com"),
//							 new User(3L,"Anand","Anand@gmail.com"),
//							 new User(4L,"Ramesh","Remesh@gmail.com"));
		return userRepository.findAll();
	}
	
	@PostMapping
	public UserEntity createUser(@RequestBody UserEntity user) {
			
		return userRepository.save(user);
	}
	
	@GetMapping("/{id}") 
	public UserEntity getUserById(@PathVariable Long id) {
		
		return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found Re:"+id));
		
	}
	@PutMapping("/{id}")
	public UserEntity updateUser(@PathVariable Long id,@RequestBody UserEntity user) {
		
		UserEntity userData = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found Re:"+id));
		
		userData.setEmail(user.getEmail());
		userData.setName(user.getName());
		return userRepository.save(userData);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		UserEntity userData = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found Re:"+id));
		userRepository.delete(userData);
		return ResponseEntity.ok().build();
	}
	
	
}
