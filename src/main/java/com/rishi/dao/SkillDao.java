package com.rishi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rishi.entities.UserSkills;

@Repository
public interface SkillDao extends CrudRepository<UserSkills, Integer> {
	public List<UserSkills> getAllByFormDetailsId(String formDetailsId);

	@Query(value = "select user_skills.name from user_skills where form_details_id= ?1", nativeQuery = true)
	public List<String> getAllNameByFormDetailsId(String formDetailsId);

	public void deleteByFormDetailsId(String id);
}
