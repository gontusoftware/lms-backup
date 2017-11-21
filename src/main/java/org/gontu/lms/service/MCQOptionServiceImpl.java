package org.gontu.lms.service;

import java.util.List;

import org.gontu.lms.dao.MCQOptionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("mcqoptionService")
@Transactional 	// service has to be transactional and not the dao as a service may use more than one dao's to 
				//get something done - and that service is needed to be transactional
public class MCQOptionServiceImpl<T> implements MCQOptionService<T> {
	
	@Autowired
	public MCQOptionDao<T> mcqoptionDao;

	@Override
	public T findById(Long id) {
		// TODO Auto-generated method stub
		return mcqoptionDao.findById(id);
	}

	@Override
	public void save(T mcqoption) {
		// TODO Auto-generated method stub
		mcqoptionDao.save(mcqoption);
	}

	@Override
	public void update(T mcqoption) {
		// TODO Auto-generated method stub
		mcqoptionDao.update(mcqoption);
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		mcqoptionDao.delete(id);
	}

	@Override
	public List<T> getAll() {
		// TODO Auto-generated method stub
		return mcqoptionDao.getAll();
	}
	
	@Override
	public boolean isExists(T entity) {
		return mcqoptionDao.isExists(entity);
	}
	
	@Override
	public void deleteAll() {
		mcqoptionDao.deleteAll();
	}	
}
