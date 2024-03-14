package com.cindyhj.todoListProject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cindyhj.todoListProject.entities.Task;

public interface TaskRepository extends JpaRepository<Task,Long>{
			
	List<Task> findByUser_Id(Long id);
}
