package com.example.itlog.objects;

import java.util.ArrayList;

import com.example.itlog.R.array;

public class Users {
	String user, pass;

	public Users() {

	}

	public Users(String user, String pass) {
		super();
		this.user = user;
		this.pass = pass;

	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public static ArrayList<Users> generateFakeUsers() {
		ArrayList<Users> arrayUsers = new ArrayList<Users>();
		arrayUsers.add(new Users("A123", "1234"));
		arrayUsers.add(new Users("C812", "1234"));
		arrayUsers.add(new Users("a", "a"));

		return arrayUsers;
	}

}
