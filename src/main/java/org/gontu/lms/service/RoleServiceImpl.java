package org.gontu.lms.service;

import java.util.List;

import org.gontu.lms.dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("roleService")
@Transactional 	// service has to be transactional and not the dao as a service may use more than one dao's to 
				//get something done - and that service is needed to be transactional
public class RoleServiceImpl<T> implements RoleService<T> {
	
	@Autowired
	public RoleDao<T> roleDao;

	@Override
	public T findById(Long id) {
		// TODO Auto-generated method stub
		return roleDao.findById(id);
	}

	@Override
	public void save(T role) {
		// TODO Auto-generated method stub
		roleDao.save(role);
	}

	@Override
	public void update(T role) {
		// TODO Auto-generated method stub
		roleDao.update(role);
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		roleDao.delete(id);
	}

	@Override
	public List<T> getAll() {
		// TODO Auto-generated method stub
		return roleDao.getAll();
	}
	
	@Override
	public boolean isExists(T entity) {
		return roleDao.isExists(entity);
	}
	
	@Override
	public void deleteAll() {
		roleDao.deleteAll();
	}	
}
