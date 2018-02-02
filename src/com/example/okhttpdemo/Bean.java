package com.example.okhttpdemo;

public class Bean {

	public String username;
	public String password;

	public Bean() {
	}

	public Bean(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	public String toString() {
		return "User{" + "username='" + username + '\'' + ", password='"
				+ password + '\'' + '}';
	}

}
