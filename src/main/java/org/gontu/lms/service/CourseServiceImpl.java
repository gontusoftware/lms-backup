package org.gontu.lms.service;

import java.util.List;

import org.gontu.lms.dao.CourseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("courseService")
@Transactional 	// service has to be transactional and not the dao as a service may use more than one dao's to 
				//get something done - and that service is needed to be transactional
public class CourseServiceImpl<T> implements CourseService<T> {
	
	@Autowired
	public CourseDao<T> courseDao;

	@Override
	public T findById(Long id) {
		// TODO Auto-generated method stub
		return courseDao.findById(id);
	}

	@Override
	public void save(T course) {
		// TODO Auto-generated method stub
		courseDao.save(course);
	}

	@Override
	public void update(T course) {
		// TODO Auto-generated method stub
		courseDao.update(course);
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		courseDao.delete(id);
	}

	@Override
	public List<T> getAll() {
		// TODO Auto-generated method stub
		return courseDao.getAll();
	}
	
	@Override
	public boolean isExists(T entity) {
		return courseDao.isExists(entity);
	}
	
	@Override
	public void deleteAll() {
		courseDao.deleteAll();
	}	
}
