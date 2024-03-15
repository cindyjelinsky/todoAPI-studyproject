package com.cindyhj.todoListProject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cindyhj.todoListProject.entities.Task;
import com.cindyhj.todoListProject.entities.User;
import com.cindyhj.todoListProject.repositories.TaskRepository;

import jakarta.transaction.Transactional;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private UserService userService;
	
	
	
	public Task findById(Long id) {
		Optional<Task> task = this.taskRepository.findById(id);
		return task.orElseThrow(() -> new RuntimeException(
			"Task Not found Id:" + id + " Tipo: " +Task.class.getName()));
	}
	
	public List<Task> findAllByUserId(Long userId){
		List<Task> tasks = 	this.taskRepository.findByUser_Id(userId);
		return tasks;
	}
	
	@Transactional
	public Task create(Task obj) {
		User user = this.userService.findById(obj.getUser().getId()); //Verifica se o usuário passado na task existe
		obj.setId(null); //cria um novo id/obj
		obj.setUser(user);
		obj = this.taskRepository.save(obj);
		return obj;
	}
	
	@Transactional
	public Task update(Task obj) {
		Task newObj = findById(obj.getId());
		newObj.setDescription(obj.getDescription());
		return this.taskRepository.save(newObj);
	}
	
	public void delete(Long id) {
		findById(id);
		try {
			this.taskRepository.deleteById(id);
		}catch(Exception e){
			throw new RuntimeException("Task not found");
			
		}
	}
	
}
