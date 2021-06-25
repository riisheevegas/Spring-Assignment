package com.rishi.entities;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.*;

@Data
public class FormDetailsSKill {
	@NotNull
	@NotEmpty
	@Email
	private String email;
	
	@NotNull
	@NotEmpty
	private String name;
	
	@Size(message = "Must have 10 digits")
	private String mobile;
	
	 	@NotEmpty
	    @NotNull
	    private String state;
	    private String gender;
	    private String photos;
	    private List<String> skills;
	    
	    
		 public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
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


		public FormDetailsSKill(@NotEmpty @Email String email, @NotEmpty String name,
				@NotEmpty @Size(min = 10, max = 10, message = "Must have 10 digits") String mobile,
				@NotEmpty String state, String gender, String photos) {
			super();
			this.email = email;
			this.name = name;
			this.mobile = mobile;
			this.state = state;
			this.gender = gender;
			this.photos = photos;
		}


		public List<String> getSkills() {
			return skills;
		}


		public void setSkills(List<String> skills) {
			this.skills = skills;
		}


		public FormDetailsSKill() {
			super();
		}


		public FormDetails getFormDetails()
		    {
		        return new FormDetails(this.email,this.name,this.mobile,this.state,this.gender,this.photos);
		    }
}
