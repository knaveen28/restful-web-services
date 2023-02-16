package com.in28minutes.rest.webservices.restfulwebservcies.user;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	private UserDaoService service;

	public UserResource(UserDaoService service) {
		this.service = service;

	}

	@GetMapping("/users")
	public List<User> retriveAllUsers() {
		return service.findAll();

	}
	
	@GetMapping("/users/{id}")
	public User retriveUser(@PathVariable int id) {
		User user = service.findOne(id);
		
		if(user==null) {
			throw new UserNotFoundException("id:"+id);
		}
		
		return user;
		

	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		User savedUser = service.save(user);
		URI Location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(Location).build();
	}
	

}
