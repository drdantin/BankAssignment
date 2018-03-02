package com.pack.model;

public class User {
	private int userID;
	private String username;
	private String password;
	private int money;
	private static boolean isUserLogged;
	private boolean overDrafted;
	
	public User(int userID, String username,String password, int money) {
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.money = money;
		isUserLogged = false;
		overDrafted = false;
	}

	public int getUserID() {
		return userID;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public int getMoney() {
		return money;
	}

	public void updateAddMoney(int money) {
		this.money += money;
	}
	
	public void upDateSubMoney(int money) {
		this.money -= money;
	}
	
	public void setIsUserLogged(boolean isLogged) {
		isUserLogged = isLogged;
	}
	
	public boolean getIsUserLogged() {
		return isUserLogged;
	}
	
	public boolean getOverDrafted() {
		return overDrafted;
	}
	
	public boolean setOverDrafted(boolean overdrafted) {
		overDrafted = overdrafted;
		return overDrafted;
	}
	
	public boolean isLogged() {
		return isUserLogged;
	}
	
	public void setLog(boolean bool) {
		isUserLogged = bool;
	}
}


