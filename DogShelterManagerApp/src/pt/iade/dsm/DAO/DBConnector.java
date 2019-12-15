package pt.iade.dsm.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connection to the database code.
 */
public class DBConnector {
	
	/** The Constant URL. */
	private static final String URL = "jdbc:mysql://remotemysql.com:3306/wlgrOv9kpn?useSSL=false";
	
	/** The Constant PASS. */
	private static final String PASS = "5nskyFTijx";
	
	/** The Constant USER. */
	private static final String USER = "wlgrOv9kpn";
	
	/*
	private static final String URL = "jdbc:mysql://localhost:3306/shelter";
	private static final String PASS = "50039011";
	private static final String USER = "root";
	*/
	
	/** The connector. */
	private static Connection connector;
	
	/**
	 * Instantiates a new Database connector.
	 */
	private DBConnector () {}
	
	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
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
