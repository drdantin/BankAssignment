package com.pack.service;
import com.pack.bankdao.AtmDatabaseDao;
import com.pack.model.User;
import com.pack.service.OverdrawnException;

public class AtmService {
	private AtmDatabaseDao dao;

	public AtmService(){
		dao = new AtmDatabaseDao();
	}

	public void createATM(int atmid, String city, int userid) {
		dao.createATM(atmid,city,userid);
	}

	/*The assignment in my understanding, wanted us to follow the DAO pattern. This pattern
	 * says that the Service part of the pattern does all business logic. In addition, that
	 * the DAO part is the only communication there is with the database. I bring this up because
	 * the login part in service needs to gain access to the database to see if the person is
	 * already in the system. However, the databse set up only allows unique additions. This
	 * userLogin does businness logic on teh list to see if the person is already in the list.
	 * if the user is not in the list - it shall than be sent to the Dao to be placed within the 
	 * database.To be able to save the list or user data locally is not known or is above what is 
	 * being asked presently. 
	 */
	public void userLogin(User user) {
		dao.userLogin(user);
	}

	public void userDeposit(User user, int depositAmount) {
		user.updateAddMoney(depositAmount);
		dao.userDeposit(user, user.getMoney());
	}

	/*This method checks to see if User can withdraw money and if the User can 
	 * withdraw money, takes the money from teh ATM. If not enough money is in the
	 * account a custome exception is thrown */
	public void userWithdrawMoney(User user, int withdraw) {
		if(user.getMoney() < withdraw)
			try {
				throw new OverdrawnException(user,withdraw);
			}catch(OverdrawnException e) {
				System.out.println(e + " Overdrawn");
			}else {
				user.upDateSubMoney(withdraw);
				dao.userWithdrawMoney(user, user.getMoney());
			}
	}

	public void getAccountView(User user) {
		dao.getAccountView(user);
	}
}


