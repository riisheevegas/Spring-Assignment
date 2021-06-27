package com.rishi.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rishi.dao.PhotoFileDao;
import com.rishi.entities.PhotoFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class PhotoMasterService {
	 @Autowired
	    private PhotoFileDao fileMasterDao;

	    public PhotoFile addFileMaster(String fileName,String userDeatilsEmail)
	    {
	        PhotoFile photFile=new PhotoFile();
	        photFile.setFileDirectory("/home/extramarks/Documents/workspace-spring-tool-suite-4-4.10.0.RELEASE/Spring-Assign/src/main/resources/static/images/"+fileName);
	        photFile.setCreated_at(LocalDateTime.now());
	        photFile.setUpdated_at(null);
	        photFile.setDeleted_at(null);
	        photFile.setFormDetailsId(userDeatilsEmail);
	        return photFile;
	    }

	    public PhotoFile addFileMaster(PhotoFile fileMaster)
	    {
	        return fileMasterDao.save(fileMaster);
	    }

	    public void deleteFileMasterById(int id)
	    {
	        fileMasterDao.deleteById(id);
	    }

	    public PhotoFile getFileMasterById(int id)
	    {
	        return fileMasterDao.findById(id).get();
	    }

	    public List<PhotoFile> getAllFileMaster()
	    {
	        return (List<PhotoFile>)fileMasterDao.findAll();
	    }

	    public void deleteByFormDetailsId(String id)
	    {
	        fileMasterDao.deleteByFormDetailsId(id);
	    }

	    @Modifying
	    public void updateFileMaster(String id,PhotoFile newFileMaster)
	    {
	        fileMasterDao.deleteByFormDetailsId(id);
	        PhotoFile fm=fileMasterDao.save(newFileMaster);
	    }
}
