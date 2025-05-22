package com.nt.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nt.domain.TaskStatus;
import com.nt.model.Task;
import com.nt.repository.TaskRepository;

@Service
public class TaskServiceImplemetaion implements TaskService {
	
	@Autowired
	private TaskRepository taskRepository;

	@Override
	public Task createTask(Task task, String requesterRole) throws Exception {
		if(!requesterRole.equals("ROLE_ADMIN")) {
			throw new Exception("only admin can create task");
		}
		task.setStatus(TaskStatus.PENDING);
		task.setCreatedAt(LocalDateTime.now());
		return taskRepository.save(task);
	}

	@Override
	public Task getTaskById(Long id) throws Exception {
		
		return taskRepository.findById(id).orElseThrow(()->new Exception("task not found with id::"+id));
	}

	@Override
	public List<Task> getAllTask(TaskStatus status) {
		 List<Task> allTask = taskRepository.findAll();

		    List<Task> filterTask = allTask.stream()
		        .filter(task -> status == null || task.getStatus().name().equalsIgnoreCase(status.toString()))
		        .collect(Collectors.toList());

		    return filterTask;
	}

	@Override
	public Task updateTask(Long id, Task updateTask, long userId) throws Exception {
		
		Task exisingTask=getTaskById(id);
		
		if(updateTask.getTitle()!=null) {
			exisingTask.setTitle(updateTask.getTitle());
		}
		if(updateTask.getImage()!=null) {
			exisingTask.setImage(updateTask.getImage());
		}
		if(updateTask.getDescription()!=null) {
			exisingTask.setDescription(updateTask.getDescription());
		}
		if(updateTask.getStatus()!=null) {
			exisingTask.setStatus(updateTask.getStatus());
		}
		if(updateTask.getDeadline()!=null) {
			exisingTask.setDeadline(updateTask.getDeadline());
		}
		if(updateTask.getCreatedAt()!=null) {
			exisingTask.setCreatedAt(updateTask.getCreatedAt());
		}
		return taskRepository.save(exisingTask);
	}

	@Override
	public void deleteTask(Long id) throws Exception {
		
		getTaskById(id);
		taskRepository.deleteById(id);
	}

	@Override
	public Task assigendToUser(Long userId, Long taskId) throws Exception {
		Task task=getTaskById(taskId);
		task.setAssigendUserId(userId);
		task.setStatus(TaskStatus.DONE);
		return taskRepository.save(task);
	}

	@Override
	public List<Task> assignedUserTask(Long userId, TaskStatus status) {
	    List<Task> allTasks = taskRepository.findByAssigendUserId(userId);

	    return allTasks.stream()
	            .filter(task -> status == null || task.getStatus().name().equalsIgnoreCase(status.toString()))
	            .collect(Collectors.toList());
	}

	@Override
	public Task CompleteTask(Long taskId) throws Exception {
		Task task=getTaskById(taskId);
		task.setStatus(TaskStatus.DONE);
		return taskRepository.save(task);
	}

}
