package com.rishi.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rishi.entities.FormDetails;

@Repository
public interface FormDetailsDao extends CrudRepository<FormDetails,String>{
	public FormDetails getUserDetailsByEmail(String email);
}
