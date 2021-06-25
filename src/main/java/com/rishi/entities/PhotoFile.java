package com.rishi.entities;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

@Entity
@Table(name="photo_file")
public class PhotoFile {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String fileDirectory;
	private LocalDateTime created_at;
	private LocalDateTime deleted_at;
	private LocalDateTime updated_at;
	private String formDetailsId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFileDirectory() {
		return fileDirectory;
	}
	public void setFileDirectory(String fileDirectory) {
		this.fileDirectory = fileDirectory;
	}
	public LocalDateTime getCreated_at() {
		return created_at;
	}
	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}
	public LocalDateTime getDeleted_at() {
		return deleted_at;
	}
	public void setDeleted_at(LocalDateTime deleted_at) {
		this.deleted_at = deleted_at;
	}
	public LocalDateTime getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}
	public String getFormDetailsId() {
		return formDetailsId;
	}
	public void setFormDetailsId(String formDetailsId) {
		this.formDetailsId = formDetailsId;
	}
	public PhotoFile(int id, String fileDirectory, LocalDateTime created_at, LocalDateTime deleted_at,
			LocalDateTime updated_at, String formDetailsId) {
		super();
		this.id = id;
		this.fileDirectory = fileDirectory;
		this.created_at = created_at;
		this.deleted_at = deleted_at;
		this.updated_at = updated_at;
		this.formDetailsId = formDetailsId;
	}
	public PhotoFile() {
		super();
	}
	@Override
	public String toString() {
		return "PhotoFile [id=" + id + ", fileDirectory=" + fileDirectory + ", created_at=" + created_at
				+ ", deleted_at=" + deleted_at + ", updated_at=" + updated_at + ", formDetailsId=" + formDetailsId
				+ "]";
	}
}
