package org.gontu.lms.service;

import java.util.List;

import org.gontu.lms.dao.VideoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("videoService")
@Transactional 	// service has to be transactional and not the dao as a service may use more than one dao's to 
				//get something done - and that service is needed to be transactional
public class VideoServiceImpl<T> implements VideoService<T> {
	
	@Autowired
	public VideoDao<T> videoDao;

	@Override
	public T findById(Long id) {
		// TODO Auto-generated method stub
		return videoDao.findById(id);
	}

	@Override
	public void save(T video) {
		// TODO Auto-generated method stub
		videoDao.save(video);
	}

	@Override
	public void update(T video) {
		// TODO Auto-generated method stub
		videoDao.update(video);
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		videoDao.delete(id);
	}

	@Override
	public List<T> getAll() {
		// TODO Auto-generated method stub
		return videoDao.getAll();
	}
	
	@Override
	public boolean isExists(T entity) {
		return videoDao.isExists(entity);
	}
	
	@Override
	public void deleteAll() {
		videoDao.deleteAll();
	}	
}
