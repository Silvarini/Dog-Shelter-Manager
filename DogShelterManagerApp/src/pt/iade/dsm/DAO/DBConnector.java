package pt.iade.dsm.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
	
	private static final String URL = "jdbc:mysql://remotemysql.com:3306/wlgrOv9kpn?useSSL=false";
	private static final String PASS = "5nskyFTijx";
	private static final String USER = "wlgrOv9kpn";
	
	/*
	private static final String URL = "jdbc:mysql://localhost:3306/shelter";
	private static final String PASS = "50039011";
	private static final String USER = "root";
	*/
	
	private static Connection connector;
	private DBConnector () {}
	
	public static Connection getConnection() {
		try {
			if (connector == null ||
					connector.isClosed())
				connector = DriverManager.
					getConnection(URL,USER,PASS);
			return connector;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
}
}
