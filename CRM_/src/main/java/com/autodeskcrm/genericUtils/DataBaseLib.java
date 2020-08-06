package com.autodeskcrm.genericUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.protocol.Resultset;
import com.mysql.jdbc.Driver;

/**
 * 
 * @author indudhara
 *
 */
public class DataBaseLib {

	Connection con ;
	public void connectToDB(String  url, String username , String password) throws Throwable{
		Driver driverref = new Driver();
		DriverManager.registerDriver(driverref);
		con = DriverManager.getConnection(url, username, password);
	}
	
	public Resultset executeQuery(String query) {
		return null;
		//execute query & return the result
	}
	
	public void dissconnectFromDB() throws SQLException {
		con.close();
	}
}
