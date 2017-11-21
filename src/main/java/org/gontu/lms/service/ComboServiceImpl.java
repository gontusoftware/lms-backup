package org.gontu.lms.service;

import java.util.List;

import org.gontu.lms.dao.ComboDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("comboService")
@Transactional 	// service has to be transactional and not the dao as a service may use more than one dao's to 
				//get something done - and that service is needed to be transactional
public class ComboServiceImpl<T> implements ComboService<T> {
	
	@Autowired
	public ComboDao<T> comboDao;

	@Override
	public T findById(Long id) {
		// TODO Auto-generated method stub
		return comboDao.findById(id);
	}

	@Override
	public void save(T combo) {
		// TODO Auto-generated method stub
		comboDao.save(combo);
	}

	@Override
	public void update(T combo) {
		// TODO Auto-generated method stub
		comboDao.update(combo);
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		comboDao.delete(id);
	}

	@Override
	public List<T> getAll() {
		// TODO Auto-generated method stub
		return comboDao.getAll();
	}
	
	@Override
	public boolean isExists(T entity) {
		return comboDao.isExists(entity);
	}
	
	@Override
	public void deleteAll() {
		comboDao.deleteAll();
	}	
}
