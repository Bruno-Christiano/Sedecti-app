package com.user.api.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.api.repository.UserRepository;
import com.user.api.models.User;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api")
public class UserResource {

	@Autowired
	UserRepository userRepository;
	
	/*list all users*/
	
	@GetMapping("/users")
	public List<User> ListAllUsers(){
		return userRepository.findAll();
	}
	
/*list by Id*/
	
	@GetMapping("/user/{id}")
	public User listaUsuarioId(@PathVariable(value = "id")long id){
		return userRepository.findById(id);
	}
	
/*Post*/
	
	@PostMapping("/user")
	public User SaveUser(@RequestBody @Valid User user){
		return userRepository.save(user);
	}
	
/*Delete */
	
	@DeleteMapping("/user/{id}")
	public void DeleteUser(@RequestBody @PathVariable(value="id") long id){
		 userRepository.deleteById(id);
		 	
	}
	
/*Update */
	
	@PutMapping("/user")
	public User UpdateData(@RequestBody @Valid User user){
		return userRepository.save(user);
	}

}
