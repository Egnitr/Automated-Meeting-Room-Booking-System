package com.hsbc.system.entity;

public class Users {
	private String name;
	private String email;
	private String phone;
	private String role;
	
	public Users() {
				
	}

	public Users(String name, String email, String phone, String role) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.role = role;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	

}
