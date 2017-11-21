package org.gontu.lms.service;

import java.util.List;

import org.gontu.lms.dao.MCQBANKDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("mcqbankService")
@Transactional 	// service has to be transactional and not the dao as a service may use more than one dao's to 
				//get something done - and that service is needed to be transactional
public class MCQBANKServiceImpl<T> implements MCQBANKService<T> {
	
	@Autowired
	public MCQBANKDao<T> mcqbankDao;

	@Override
	public T findById(Long id) {
		// TODO Auto-generated method stub
		return mcqbankDao.findById(id);
	}

	@Override
	public void save(T mcqbank) {
		// TODO Auto-generated method stub
		mcqbankDao.save(mcqbank);
	}

	@Override
	public void update(T mcqbank) {
		// TODO Auto-generated method stub
		mcqbankDao.update(mcqbank);
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		mcqbankDao.delete(id);
	}

	@Override
	public List<T> getAll() {
		// TODO Auto-generated method stub
		return mcqbankDao.getAll();
	}
	
	@Override
	public boolean isExists(T entity) {
		return mcqbankDao.isExists(entity);
	}
	
	@Override
	public void deleteAll() {
		mcqbankDao.deleteAll();
	}	
}
