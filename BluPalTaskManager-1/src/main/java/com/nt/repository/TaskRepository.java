package com.nt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
	
	List<Task> findByAssigendUserId(Long userId);
}
