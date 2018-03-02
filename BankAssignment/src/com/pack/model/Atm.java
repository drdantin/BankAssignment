package com.pack.model;
import com.pack.service.*;
	
	/*Issues: I was continuesly trying to send the ATM data to the table
	 * (as createatm) yet the user had the primary key and the user had to be pushed first to the 
	 * database prior from me "creating an atm." -Issue with continually putting in different users
	 * because of constraint issues.
	 *  
	 */
	public class Atm {
		private int atmID;
		private int userid;
		private String city;
		private AtmService service;
		
		public Atm(int atmID, String city, int userid) {
			super();
			service = new AtmService();
			this.atmID = atmID;
			this.userid = userid;
			this.city = city;
			
		}
		/*User Login must be done first prior from createATM method*/
		public void userLogin(User user) {
			System.out.println();
			System.out.println("Logging in ...");
			System.out.println();
			service.userLogin(user);
		}
		
		public void createATM(int atmid,String city,int userid) {
			System.out.println();
			service.createATM(atmid, city, userid);
		}	
		
		public void userDeposit(User user, int depositAmount) {
			System.out.println();
			service.userDeposit(user, depositAmount);
		}
		
		//This method invokes ATMservice method userWithdrawMoney
		public void userWithdrawMoney(User user, int withdraw) {
			System.out.println(user.getUsername() + " withdrawing "+ withdraw + " $  ...");
			System.out.println();
			service.userWithdrawMoney(user, withdraw);
		}
		
		public void getAccountView(User user) {
			System.out.println();
			service.getAccountView(user);
		}

		public int getAtmID() {
			return atmID;
		}
		
		public void setAtmID( int set) {
			this.atmID = set;
		}
		
		public int getUserId() {
			return userid;
		}
		
		public void setUserID(int set) {
			this.userid = set;
		}

		public String getCity() {
			return city;
		}
	}


