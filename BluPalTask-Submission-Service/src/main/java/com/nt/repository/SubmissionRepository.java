package com.nt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.model.Submission;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
	
	public List<Submission> findByTaskId(Long taskId);
}
