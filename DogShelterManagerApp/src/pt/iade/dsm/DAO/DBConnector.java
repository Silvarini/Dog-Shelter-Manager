package pt.iade.dsm.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connection to the database code.
 */
public class DBConnector {
	
	/** The Constant URL. */
	private static final String URL = "jdbc:mysql://remotemysql.com:3306/hBEttxy9DD?useSSL=false";
	
	/** The Constant PASS. */
	private static final String PASS = "tqHSXgfWpQ";
	
	/** The Constant USER. */
	private static final String USER = "hBEttxy9DD";
	
	
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
