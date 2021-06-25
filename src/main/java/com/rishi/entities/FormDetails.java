package com.rishi.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;
@Entity
@Table(name="form_details")
public class FormDetails {
	@Id
	private String name;
	private String email;
	private String mobile;
	private String state;
	private String gender;
	private String photos;
	@Override
	public String toString() {
		return "FormDetails [name=" + name + ", email=" + email + ", mobile=" + mobile + ", state=" + state
				+ ", gender=" + gender + ", photos=" + photos + "]";
	}
	public FormDetails() {
		super();
	}
	public FormDetails(String name, String email, String mobile, String state, String gender, String photos) {
		super();
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.state = state;
		this.gender = gender;
		this.photos = photos;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhotos() {
		return photos;
	}
	public void setPhotos(String photos) {
		this.photos = photos;
	}
}