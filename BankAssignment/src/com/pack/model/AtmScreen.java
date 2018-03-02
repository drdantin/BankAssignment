package com.pack.model;
import java.util.Random;
import java.util.Scanner;

import com.pack.bankdao.AtmDatabaseDao;
import com.pack.service.AtmService;

public class AtmScreen {

	private Scanner input;
	private Random rand;
	private Atm atm;
	private String username;
	private String password;
	private int userid;
	private String city;
	private AtmDatabaseDao dao;
	private User usr;
	private AtmService serviceGo;

	public AtmScreen() {
		input = new Scanner(System.in);
		rand = new Random();
		city = "Tampa Bay";
		serviceGo = new AtmService();
		atm = new Atm(0,city,0);
		dao = new AtmDatabaseDao();
		usr = null;
	}

	public void greeting() {
		int number;
		System.out.println("\tWelcome");
		System.out.println("1 <-- Login");
		System.out.println("2 <-- Register");
		System.out.println("0 <-- Logout/Exit");
		number = input.nextInt();

		switch(number) {
		case 1: loginScreen();
		break;
		case 2: registerScreen();
		break;
		case 0: System.out.println("Have a good day.");

		break;
		}
	}

	public void loginScreen() {

		System.out.println("Your username ?");
		username = input.next();
		System.out.println("Your password");
		password = input.next();

		usr = dao.getDataInfo(username, password);

		if(usr == null) {
			System.out.println("You need to register.");
			System.out.println();
			greeting();
		}else {
			greetingChoice(usr);
		}

	}

	public void registerScreen() {
		int useridRand = rand.nextInt(1000) + 1;
		int atmidRand = rand.nextInt(1000) +1;
		System.out.println("Your username ?");
		username = input.next();
		System.out.println("Your password ?");
		password = input.next();
		User user = new User(useridRand,username,password,0);
		atm.setAtmID(atmidRand);
		atm.setUserID(useridRand);
		atm.userLogin(user);
		atm.createATM(atm.getAtmID(), city, useridRand);
		
		greetingChoice(user);
	}

	public void greetingChoice(User user) {

		int balance = 0;
		int withdraw = 0;
		int number;
		do {
			System.out.println("1 <-- Deposit");
			System.out.println("2 <-- WithDraw");
			System.out.println("3 <-- View");
			System.out.println("0 <-- Logout/Exit");
			number = input.nextInt();

			switch(number) {
			case 1: System.out.println("How much would you like to deposit?");
			balance = input.nextInt();
			atm.userDeposit(user, balance);
			break;
			case 2: System.out.println("How much would you like to withdraw?");
			withdraw = input.nextInt();
			atm.userWithdrawMoney(user, withdraw);
			break;
			case 3: System.out.println("View");
			atm.getAccountView(user);
			break;
			case 0: System.out.println("Have a good day.");
			
			default:
			}
		}while(number != 0);
	}

	public static void main(String[] args) {
		AtmScreen screen = new AtmScreen();
		screen.greeting();
	}	
}
