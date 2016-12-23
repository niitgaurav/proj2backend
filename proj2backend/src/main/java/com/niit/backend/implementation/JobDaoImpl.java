package com.niit.backend.implementation;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.backend.dao.JobDao;
import com.niit.backend.model.Job;
import com.niit.backend.model.User;

@Repository
public class JobDaoImpl implements JobDao 
{
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void postJob(Job job) {
		Session session=sessionFactory.openSession();
		session.save(job);
		session.flush();
		session.close();
	}

	@Override
	public List<Job> getAllJobs() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Job where status='approved' and hasexpired='no'");
		List<Job> jobs=query.list();
		session.close();
		return jobs;
	}

	@Override
	public Job getJobById(int id) {
		Session session = sessionFactory.openSession();
		// select * from personinfo where id=2
		Job job = (Job) session.get(Job.class, id);
		session.close();
		return job;
		
	}

	@Override
	public Job updateJob(int id, Job job) {
		Session session = sessionFactory.openSession();
		System.out.println("Id of Job is to update is: " + job.getJobId());
		if (session.get(Job.class, id) == null)
			return null;
		session.merge(job); // update query where personid=?
		// select [after modification]
		Job updatedJob = (Job) session.get(Job.class, id); // select query
		session.flush();
		session.close();
		return updatedJob;
		
	}

	@Override
	public void deleteJob(int id) {
		Session session = sessionFactory.openSession();
		// make the object persistent - job
		Job job = (Job) session.get(Job.class, id);
		session.delete(job);
		// Transient - job
		session.flush();
		session.close();
		
	}

	@Override
	public List<Job> getJobByStatus(String status) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Job where status ='" + status + "'");
		List<Job> jobs=query.list();
		session.close();
		return jobs;
	}

	@Override
	public List<Job> getJobByExpiry(String hasexpired) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Job where hasexpired ='" + hasexpired + "'");
		List<Job> jobs=query.list();
		session.close();
		return jobs;
	}
}

