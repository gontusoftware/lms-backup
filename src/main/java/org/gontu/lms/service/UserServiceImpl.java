package org.gontu.lms.service;

import java.util.List;

import org.gontu.lms.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional 	// service has to be transactional and not the dao as a service may use more than one dao's to 
				//get something done - and that service is needed to be transactional
public class UserServiceImpl<T> implements UserService<T> {
	
	@Autowired
	public UserDao<T> userDao;

	@Override
	public T findById(Long id) {
		// TODO Auto-generated method stub
		return userDao.findById(id);
	}

	@Override
	public void save(T user) {
		// TODO Auto-generated method stub
		userDao.save(user);
	}

	@Override
	public void update(T user) {
		// TODO Auto-generated method stub
		userDao.update(user);
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		userDao.delete(id);
	}

	@Override
	public List<T> getAll() {
		// TODO Auto-generated method stub
		return userDao.getAll();
	}
	
	@Override
	public boolean isExists(T entity) {
		return userDao.isExists(entity);
	}
	
	@Override
	public void deleteAll() {
		userDao.deleteAll();
	}	
}
