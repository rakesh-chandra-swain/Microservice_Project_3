package com.nt.service;

import java.util.List;

import com.nt.domain.TaskStatus;
import com.nt.model.Task;

public interface TaskService {
	
	public Task createTask(Task task,String requesterRole)throws Exception;
	public Task getTaskById(Long id)throws Exception;
	public List<Task> getAllTask(TaskStatus status);
	public Task updateTask(Long id,Task updateTask,long userId)throws Exception;
	public void deleteTask(Long id) throws Exception;
	public Task assigendToUser(Long userId,Long taskId)throws Exception;
	public List<Task> assignedUserTask(Long userId,TaskStatus status);
	public Task CompleteTask(Long taskId)throws Exception;
}
