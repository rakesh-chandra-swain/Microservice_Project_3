package com.nt.model;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Submission_Database")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Submission {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long taskId;
	private String githubLink;
	private Long userId;
	private String status="PENDING";
	private LocalDateTime submissionTime;

}
