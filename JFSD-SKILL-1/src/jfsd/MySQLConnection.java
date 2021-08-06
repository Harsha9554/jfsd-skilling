package jfsd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLConnection {
	private String url = "jdbc:mysql://localhost:3306/jfsd_sk_1";
	private String user = "root";
	private String password = "Qwerty@528491";

	public Connection connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, password);
			return con;
		} catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}
}
