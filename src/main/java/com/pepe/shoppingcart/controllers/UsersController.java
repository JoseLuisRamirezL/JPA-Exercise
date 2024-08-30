package com.pepe.shoppingcart.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pepe.shoppingcart.dto.UsersRequest;
import com.pepe.shoppingcart.dto.UsersUpdateRequest;
import com.pepe.shoppingcart.exception.UserNotFoundException;
import com.pepe.shoppingcart.model.Users;
import com.pepe.shoppingcart.model.WishList;
import com.pepe.shoppingcart.repository.UsersRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UsersController {
	private UsersRepository repository;
	
	@Autowired
	UsersController(UsersRepository repository){
		this.repository = repository;
	}
	
	//Method email
	private boolean emailExists(String email) {
		return repository.findByEmail(email).isPresent();
	}
	
	// Create new User
	@RequestMapping(value="/users",method=RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UsersRequest request){
		Users users = new Users();
		users.setUserName(request.getUserName());
		users.setEmail(request.getEmail());
		users.setAreaOfInterest(request.getAreaOfInterest());
		// Verify email, I used an exception to notify.
		if(emailExists(users.getEmail())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already in use");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(users));
	}
	
	// Update user, I used the same method to verify the email.
	@RequestMapping(value="/users/update/{id}", method=RequestMethod.PUT)
	public ResponseEntity<?> updateUser(
			@PathVariable("id") Long id,
			@RequestBody UsersUpdateRequest request){
		
		Users users = repository.findById(id).orElse(null);
		if (users == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("There is no user with that id.");
		}
		users.setEmail(request.getEmail());
		users.setAreaOfInterest(request.getAreaOfInterest());
		if (emailExists(users.getEmail())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already in use");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(users));
	}
	
	//Delete user
	@RequestMapping(value="/users/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUsers(@PathVariable("id") Long id) {
		Users users = repository.findById(id).orElse(null);
		if (users == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("There is no user with that id.");
		}
		repository.deleteById(id);
		return ResponseEntity.status(HttpStatus.CREATED).body("User deleted");
	}
	
	//List of all users
	@RequestMapping(value = "/users", method=RequestMethod.GET)
	public ResponseEntity<?> getAllUsers(){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.findAll());
	}
	
	// Get a user by name
	@RequestMapping(value = "/users/userName/{userName}", method = RequestMethod.GET)
	public ResponseEntity<?> getUserName(@PathVariable("userName") String userName) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.findByUserName(userName));
	}

	// Get a user by email
	@RequestMapping(value = "/users/email/{email}", method = RequestMethod.GET)
	public ResponseEntity<?> getUserEmail(@PathVariable("email") String email) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.findByEmail(email));
	}
	
	
	//EXERCISE 2 ----------------------------------------------------------------------------
	@GetMapping("/users/{id}/wishlist")
	public List<WishList> retrieveWishList(@PathVariable Long id){
		Optional<Users> user = repository.findById(id);
		if(user.isEmpty())
			throw new UserNotFoundException("id"+id);
		return user.get().getWishList();
	}
}
