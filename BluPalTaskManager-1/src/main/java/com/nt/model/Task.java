package com.nt.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.nt.domain.TaskStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Task_Database")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	private String title;
	 private String description;
	 private String image;
	 private Long assigendUserId;
	 private List<String> tags=new ArrayList<>();
	 private TaskStatus status;
	 private LocalDateTime deadline;
	 private LocalDateTime createdAt;

}
