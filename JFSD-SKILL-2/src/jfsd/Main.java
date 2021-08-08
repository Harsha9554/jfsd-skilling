package jfsd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static Connection con = new MySQLConnection().connect();
	public static void main(String args[]) throws SQLException, FileNotFoundException, ParseException {
		boolean flag = true;
		System.out.println("Welcom to K L Univeristy Hostel Portal\n");
		while(flag) {
			System.out.println("1. Add Student");
			System.out.println("2. Delete Student");
			System.out.println("3. Update Student");
			System.out.println("4. View Student Details");
			System.out.println("5. Quit\n");
			int ch = Integer.parseInt(sc.nextLine());
			switch(ch) {
				case 1: addStudent();
						break;
				case 2: deleteStudent();
						break;
				case 3: updateStudent();
						break;
				case 4: viewStudenDetails();
						break;
				case 5: System.out.println("BYE!");
				flag = false;
				System.exit(0);
				break;
			}
		}
	}
	private static void addStudent() throws SQLException, ParseException, FileNotFoundException {
		PreparedStatement ps = con.prepareStatement("INSERT INTO hostel_student VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		System.out.println("Enter Student Details : (ID, Name, Gender, Year, Department, Mobile, DOB (dd/MM/yyyy), Parent's Name, Parent's Mobile, Address, KL ID Image Path, Payment Receipt Path)\n");
		int id = Integer.parseInt(sc.nextLine());
		String name = sc.nextLine();
		String gender = sc.nextLine();
		int year = Integer.parseInt(sc.nextLine());
		String department = sc.nextLine();
//		int mobile = Integer.parseInt(sc.nextLine());
		String mobile = sc.nextLine();
		String dob = sc.nextLine();
		java.sql.Date dobDate = new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse(dob).getTime());
		String parentName = sc.nextLine();
//		int parentMobile = Integer.parseInt(sc.nextLine());
		String parentMobile = sc.nextLine();
		String address = sc.nextLine();

//		/home/harsha9554/code/eclipse-workspace/JFSD-SKILL-2/src/static/kl-id-card.png
		File image = new File(sc.nextLine());
		FileInputStream imageFS = new FileInputStream(image);
		
//		/home/harsha9554/code/eclipse-workspace/JFSD-SKILL-2/src/static/hostel-payment.pdf
		File payment = new File(sc.nextLine());
		FileInputStream paymentFS = new FileInputStream(payment);
		
		ps.setInt(1, id);
		ps.setString(2, name);
		ps.setString(3, gender);
		ps.setInt(4, year);
		ps.setString(5, department);
		ps.setString(6, mobile);
		ps.setDate(7,  dobDate);
		ps.setString(8, parentName);
		ps.setString(9, parentMobile);
		ps.setString(10, address);
		ps.setBlob(11, imageFS);
		ps.setBlob(12, paymentFS);
		
		int b = ps.executeUpdate();
		if (b > 0) 
			System.out.println("Student added SUCCESSFULLY!\n");
		else System.out.println("Execution Failed");

	}
	private static void deleteStudent() throws SQLException {
		System.out.println("Enter Student's ID\n");
		int id = Integer.parseInt(sc.nextLine());
		
		PreparedStatement ps = con.prepareStatement("DELETE FROM hostel_student WHERE id=?");
		ps.setInt(1, id);
		
		int b = ps.executeUpdate();
		if (b > 0) 
			System.out.println("Student removed SUCCESSFULLY!\n");
		else System.out.println("Execution Failed");
		
	}
	private static void updateStudent() throws SQLException, FileNotFoundException, ParseException {
		System.out.println("Enter Student's ID\n");
		int id = Integer.parseInt(sc.nextLine());
		
		PreparedStatement ps = con.prepareStatement("DELETE FROM hostel_student WHERE id=?");
		ps.setInt(1, id);
		
		int b = ps.executeUpdate();
		if (b > 0) {
			addStudent();
		} else {
			System.out.println("Execution Failed!\n");
		}

	}
	private static void viewStudenDetails() throws SQLException {
		System.out.println("K L Hostel Students Detials");
		PreparedStatement ps = con.prepareStatement("SELECT * FROM hostel_student");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			System.out.println();
        	System.out.println("ID             : " + rs.getInt(1));
        	System.out.println("Name           : " + rs.getString(2));
        	System.out.println("Gender         : " + rs.getString(3));
        	System.out.println("Year           : " + rs.getInt(4));
        	System.out.println("Department     : " + rs.getString(5));
        	System.out.println("Mobile         : " + rs.getString(6));
        	System.out.println("DOB            : " + rs.getDate(7));
        	System.out.println("Parent Name    : " + rs.getString(8));
        	System.out.println("Parent Mobile  : " + rs.getString(9));
        	System.out.println("Address        : " + rs.getString(10));
			System.out.println();
		}
	}
}
