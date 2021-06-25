package com.rishi.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.rishi.dao.FormDetailsDao;
import com.rishi.entities.FormDetails;

@Service
@Transactional
public class FormDetailsService {
	
	    @Autowired
	    private FormDetailsDao userDetailsDao;

	    public FormDetails addUserDetails(FormDetails userDetails)
	    {
	        return userDetailsDao.save(userDetails);
	    }

	    public FormDetails getUserDetailsByEmail(String email)
	    {
	        return userDetailsDao.getUserDetailsByEmail(email);
	    }

	    @Modifying
	    public void updateUserDetails(String id,FormDetails newUserDetails)
	    {
	        userDetailsDao.deleteById(id);
	        FormDetails ud=userDetailsDao.save(newUserDetails);
	    }
}
