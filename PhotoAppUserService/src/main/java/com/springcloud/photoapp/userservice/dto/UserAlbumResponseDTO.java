package com.springcloud.photoapp.userservice.dto;

import java.util.List;

public class UserAlbumResponseDTO {

	private String firstName;
	private String lastName;
	private String email;
	private String userId;
	private List<AlbumResponseModel> albums;
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UserAlbumResponseDTO [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", userId=" + userId + ", albums=" + albums + "]";
	}

	

	public List<AlbumResponseModel> getAlbums() {
		return albums;
	}

	public void setAlbums(List<AlbumResponseModel> albums) {
		this.albums = albums;
	}

	
}
