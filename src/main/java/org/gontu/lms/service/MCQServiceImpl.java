package org.gontu.lms.service;

import java.util.List;

import org.gontu.lms.dao.MCQDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("mcqService")
@Transactional 	// service has to be transactional and not the dao as a service may use more than one dao's to 
				//get something done - and that service is needed to be transactional
public class MCQServiceImpl<T> implements MCQService<T> {
	
	@Autowired
	public MCQDao<T> mcqDao;

	@Override
	public T findById(Long id) {
		// TODO Auto-generated method stub
		return mcqDao.findById(id);
	}

	@Override
	public void save(T mcq) {
		// TODO Auto-generated method stub
		mcqDao.save(mcq);
	}

	@Override
	public void update(T mcq) {
		// TODO Auto-generated method stub
		mcqDao.update(mcq);
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		mcqDao.delete(id);
	}

	@Override
	public List<T> getAll() {
		// TODO Auto-generated method stub
		return mcqDao.getAll();
	}
	
	@Override
	public boolean isExists(T entity) {
		return mcqDao.isExists(entity);
	}
	
	@Override
	public void deleteAll() {
		mcqDao.deleteAll();
	}	
}
