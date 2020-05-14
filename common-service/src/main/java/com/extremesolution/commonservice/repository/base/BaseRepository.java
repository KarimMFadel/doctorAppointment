package com.extremesolution.commonservice.repository.base;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface BaseRepository<T, ID> extends MongoRepository<T, ID> {

	public List<T> findByRetireFalse();
	
}
