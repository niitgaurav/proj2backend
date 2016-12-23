package com.niit.backend.dao;

import java.util.List;

import com.niit.backend.model.Job;
import com.niit.backend.model.User;

public interface JobDao {
	void postJob(Job job);
	Job getJobById(int id);
	List<Job> getAllJobs();
	Job updateJob(int id, Job job);
	void deleteJob(int id);	
	
	List<Job> getJobByStatus(String status);
	List<Job> getJobByExpiry(String hasexpired);
	
}
