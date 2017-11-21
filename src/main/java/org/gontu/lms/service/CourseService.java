package org.gontu.lms.service;

import java.util.List;

public interface CourseService<T> {

	T findById(Long id);

	void save(T user);

	void update(T user);

	void delete(long id);

	List<T> getAll();

	void deleteAll();

	boolean isExists(T entity);

}
