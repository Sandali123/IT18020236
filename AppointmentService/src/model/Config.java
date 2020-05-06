package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class Config {

	public Connection connect() {
		
		Connection con = null;
		try {
			//Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/appointment", "root", "");
			
			//System.out.println("Connection Successful");
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		 
		return con;
	}
	/*
	 * public static void main(String[] args) { Config config = new Config();
	 * Connection con = config.connect(); }
	 */

}
