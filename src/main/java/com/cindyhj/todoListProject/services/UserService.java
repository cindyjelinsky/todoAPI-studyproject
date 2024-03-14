package com.cindyhj.todoListProject.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cindyhj.todoListProject.entities.User;

import com.cindyhj.todoListProject.repositories.UserRepository;

import jakarta.transaction.Transactional;



@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	private User findById(Long id) {
		Optional<User> user = this.userRepository.findById(id);
		return user.orElseThrow(() -> new RuntimeException(
				"User Not found Id:" + id + " Tipo: " + User.class.getName()));
	}
	
	@Transactional
	public User create (User obj) {
		obj.setId(null);
		obj = this.userRepository.save(obj);
		return obj;
	}
	
	@Transactional
	public User update(User obj) {
		User newObj = findById(obj.getId());
		newObj.setPassword(obj.getPassword());
		return this.userRepository.save(newObj);
	}
	
	public void delete(Long id) {
		findById(id);
		try {
			this.userRepository.deleteById(id);
		}catch(Exception e) {
			throw new RuntimeException("Not possible to delete User with entitites related");
		}
	}
}
