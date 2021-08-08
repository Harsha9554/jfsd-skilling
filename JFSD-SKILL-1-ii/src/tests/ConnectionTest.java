package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import jfsd.MySQLConnection;

public class ConnectionTest {
	MySQLConnection my = new MySQLConnection("wrong_url", "wrong_name", "wrong_password");
	@Test
	public void wrongConnectionTest() {
		assertEquals(null, my.connect());
	}

}
