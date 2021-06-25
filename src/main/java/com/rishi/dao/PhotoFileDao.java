package com.rishi.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rishi.entities.PhotoFile;

@Repository
public interface PhotoFileDao extends CrudRepository<PhotoFile,Integer>{
	public void deleteByFormDetailsId(String id);
}
