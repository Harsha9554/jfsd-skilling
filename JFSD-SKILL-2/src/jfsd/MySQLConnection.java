package jfsd;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnection {
	private String url = "jdbc:mysql://localhost:3306/jfsd_sk_2";
	private String user = "root";
	private String password = "Qwerty@528491";
	
	public MySQLConnection(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}
	
	public MySQLConnection( ) {}

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
