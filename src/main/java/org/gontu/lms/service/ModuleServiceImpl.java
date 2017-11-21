package org.gontu.lms.service;

import java.util.List;

import org.gontu.lms.dao.ModuleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("moduleService")
@Transactional 	// service has to be transactional and not the dao as a service may use more than one dao's to 
				//get something done - and that service is needed to be transactional
public class ModuleServiceImpl<T> implements ModuleService<T> {
	
	@Autowired
	public ModuleDao<T> moduleDao;

	@Override
	public T findById(Long id) {
		// TODO Auto-generated method stub
		return moduleDao.findById(id);
	}

	@Override
	public void save(T module) {
		// TODO Auto-generated method stub
		moduleDao.save(module);
	}

	@Override
	public void update(T module) {
		// TODO Auto-generated method stub
		moduleDao.update(module);
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		moduleDao.delete(id);
	}

	@Override
	public List<T> getAll() {
		// TODO Auto-generated method stub
		return moduleDao.getAll();
	}
	
	@Override
	public boolean isExists(T entity) {
		return moduleDao.isExists(entity);
	}
	
	@Override
	public void deleteAll() {
		moduleDao.deleteAll();
	}	
}
