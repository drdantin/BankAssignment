package com.pack.bankdao;


	import java.sql.CallableStatement;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import com.pack.model.User;
	import com.pack.service.AtmService;

	public class AtmDatabaseDao {
		                                         
		private static String url= "jdbc:oracle:thin:@usfdatabase.crmoxg68rqs9.us-east-2.rds.amazonaws.com:1521:orcl";
		private static String username= "bankassignment";
		private static String password= "bank4";
//		static{ try {
//	         Class.forName("oracle.jdbc.driver.OracleDriver");
//	     } catch (ClassNotFoundException e) {
//	         e.printStackTrace();
//	     }
//		}
	 
		public User getDataInfo(String user_name, String passWord) {
			User usr = null;
			try(Connection conn = DriverManager.getConnection(url, username, password);){
				String sql= "Select userid,username,pass_word,balance from atmuser where username = " + 
						"'" + user_name + "'"; 
				PreparedStatement stmt = conn.prepareCall(sql);
				ResultSet rset = stmt.executeQuery();

				String userid = "";
				String userName = "";
				String pass_Word = "";
				String balance = "";
				
				while(rset.next()) {
					userid = rset.getString(1);
					userName = rset.getString(2);
					pass_Word = rset.getString(3);
					balance = rset.getString(4);
				}
				if(userid != "") {
					int getUserid = Integer.parseInt(userid);
					int getBalance = Integer.parseInt(balance);
					usr = new User(getUserid,userName,pass_Word,getBalance);
					usr.setIsUserLogged(true);
					
					//ATMService.userLoggedInList.add(usr);
				}
				conn.close();
				stmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
			return usr;
		}
		
		
		public void createATM(int atmid,String city,int userid) {

			try(Connection conn = DriverManager.getConnection(url, username, password);)
			{
				String sql = "INSERT INTO atm(atmid,city,userid) VALUES (?,?,?)";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, atmid);  //starts at 1
				ps.setString(2, city);
				ps.setInt(3, userid);
				ps.executeUpdate();
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}

		public void userLogin(User user) {
			int userid = user.getUserID();
			String user_name = user.getUsername();
			String pass = user.getPassword();
			int balance = user.getMoney();

			try(Connection conn = DriverManager.getConnection(url, username, password);)
			{
				String sql = "INSERT INTO atmuser(userid, username, pass_word, balance) VALUES (?,?,?,?)";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setLong(1, userid);  
				ps.setString(2, user_name);
				ps.setString(3, pass);
				ps.setLong(4, balance);
				ps.executeUpdate();
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}

		public void userDeposit(User user, int depositAmount) {
			String userName = user.getUsername();
			int deposit = depositAmount;

			try(Connection conn = DriverManager.getConnection(url, username, password);)
			{
				String sql = "UPDATE atmuser SET balance = ? WHERE username = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setLong(1, deposit);  //starts at 1
				ps.setString(2, userName);
				ps.executeUpdate();
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}

		public void userWithdrawMoney(User user, int withdraw) {
			String userName = user.getUsername();
			int balance = withdraw;
			try(Connection conn = DriverManager.getConnection(url, username, password);)
			{
				String sql = "UPDATE atmuser SET balance = ? WHERE username = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setLong(1, balance);  //starts at 1
				ps.setString(2, userName);
				ps.executeUpdate();
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}

		public void getAccountView(User user) {
//			CallableStatement callableStatement =
//				    connection.prepareCall("{call calculateStatistics(?, ?)}");
	//
//				callableStatement.setString(1, "param1");
//				callableStatement.setInt   (2, 123);
			try(Connection conn = DriverManager.getConnection(url, username, password);)
			{
				CallableStatement callableStatement =
					conn.prepareCall("{call whatever(?,?)}");
		
					callableStatement.setInt(1, 526);
					callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
					//callableStatement.getString("'username'");
				
				ResultSet rset = callableStatement.executeQuery();
				while(rset.next()) {
					System.out.println("\t\tVIEW");
					System.out.println();
					System.out.println(" User name : " + rset.getString(1));
//					System.out.println(" User Password : " + rset.getString(2));
//					System.out.println(" User Balance : " + rset.getString(3));
					System.out.println();
				}
				conn.close();
				callableStatement.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

