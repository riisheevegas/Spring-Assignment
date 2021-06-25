package com.rishi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

@Entity
@Table(name="user_skills")
public class UserSkills {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private String formDetailsId;
	public UserSkills() {
		super();
	}
	public UserSkills(int id, String name, String formDetailsId) {
		super();
		this.id = id;
		this.name = name;
		this.formDetailsId = formDetailsId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFormDetailsId() {
		return formDetailsId;
	}
	public void setFormDetailsId(String formDetailsId) {
		this.formDetailsId = formDetailsId;
	}
	@Override
	public String toString() {
		return "UserSkills [id=" + id + ", name=" + name + ", formDetailsId=" + formDetailsId + "]";
	}
}
