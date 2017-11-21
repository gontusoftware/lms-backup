package org.gontu.lms.service;

import java.util.List;

import org.gontu.lms.dao.AssignmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("assignmentService")
@Transactional 	// service has to be transactional and not the dao as a service may use more than one dao's to 
				//get something done - and that service is needed to be transactional
public class AssignmentServiceImpl<T> implements AssignmentService<T> {
	
	@Autowired
	public AssignmentDao<T> assignmentDao;

	@Override
	public T findById(Long id) {
		// TODO Auto-generated method stub
		return assignmentDao.findById(id);
	}

	@Override
	public void save(T assignment) {
		// TODO Auto-generated method stub
		assignmentDao.save(assignment);
	}

	@Override
	public void update(T assignment) {
		// TODO Auto-generated method stub
		assignmentDao.update(assignment);
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		assignmentDao.delete(id);
	}

	@Override
	public List<T> getAll() {
		// TODO Auto-generated method stub
		return assignmentDao.getAll();
	}
	
	@Override
	public boolean isExists(T entity) {
		return assignmentDao.isExists(entity);
	}
	
	@Override
	public void deleteAll() {
		assignmentDao.deleteAll();
	}	
}
