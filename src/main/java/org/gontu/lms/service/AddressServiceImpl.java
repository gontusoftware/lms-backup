package org.gontu.lms.service;

import java.util.List;

import org.gontu.lms.dao.AddressDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("addressService")
@Transactional 	// service has to be transactional and not the dao as a service may use more than one dao's to 
				//get something done - and that service is needed to be transactional
public class AddressServiceImpl<T> implements AddressService<T> {
	
	@Autowired
	public AddressDao<T> addressDao;

	@Override
	public T findById(Long id) {
		// TODO Auto-generated method stub
		return addressDao.findById(id);
	}

	@Override
	public void save(T address) {
		// TODO Auto-generated method stub
		addressDao.save(address);
	}

	@Override
	public void update(T address) {
		// TODO Auto-generated method stub
		addressDao.update(address);
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		addressDao.delete(id);
	}

	@Override
	public List<T> getAll() {
		// TODO Auto-generated method stub
		return addressDao.getAll();
	}
	
	@Override
	public boolean isExists(T entity) {
		return addressDao.isExists(entity);
	}
	
	@Override
	public void deleteAll() {
		addressDao.deleteAll();
	}	
}
