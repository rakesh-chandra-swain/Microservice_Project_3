package com.nt.service;

import java.util.List;

import com.nt.model.Submission;

public interface SubmissionService {
	
	public Submission submitTask(Long taskId,String githubLink,Long userId,String jwt) throws Exception;
	public Submission getTaskSubmissionById(Long submissionId) throws Exception;
	public List<Submission> getAllTaskSubmissions();
	public List<Submission> getTaskSubmissionByTaskId(Long taskId);
	public Submission acceptDeclinesubmission(Long id,String status) throws Exception;
}
