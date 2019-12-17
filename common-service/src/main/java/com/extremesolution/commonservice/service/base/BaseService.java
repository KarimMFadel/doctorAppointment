package com.extremesolution.commonservice.service.base;

import java.util.List;

public interface BaseService<T> {
	
	public String save(T object);
	public void update(String id, T object);
	public T get(String id);
	public void delete(String id);
	public List<T> getAllDocuments();
}
