package com.pack.service;
import com.pack.model.User;
import com.pack.service.OverdrawnException;

public class OverdrawnException extends Exception {
	private int detail;
	private User user;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//a is too much cut off- lower than 7
	public OverdrawnException(User user, int a) {
		this.detail = a;
		this.user = user;
	}

	@Override
	public String toString() {
		String temp = " User "+ user.getUsername() + " the amount of " + detail +
				" withdrawal has exceeded means.";
		return temp;
	}
}


