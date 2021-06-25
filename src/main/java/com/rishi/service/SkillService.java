package com.rishi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rishi.dao.SkillDao;
import com.rishi.entities.UserSkills;

import java.util.List;

@Service
@Transactional
public class SkillService {
    @Autowired
    private SkillDao skillDao;

    public UserSkills addSkill(String skill,String userDetailsEmail)
    {
        UserSkills currSkill=new UserSkills();
        currSkill.setName(skill);
        currSkill.setFormDetailsId(userDetailsEmail);
        return currSkill;
    }

    public UserSkills addSkill(UserSkills skill)
    {
        return skillDao.save(skill);
    }

    public List<UserSkills> getAllByFormDetailsId(String formDetailsId)
    {
        return skillDao.getAllByFormDetailsId(formDetailsId);
    }

    public List<String> getAllNameByFormDetailsId(String formDetailsId)
    {
        return  skillDao.getAllNameByFormDetailsId(formDetailsId);
    }

    public void deleteByFormDetailsId(String id)
    {
        skillDao.deleteByFormDetailsId(id);
    }

    @Modifying
    public void updateSkill(String id,UserSkills newSkill)
    {
        skillDao.deleteByFormDetailsId(id);
        UserSkills sk=skillDao.save(newSkill);
    }
}
