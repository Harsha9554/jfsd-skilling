package jfsd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static Connection con = new MySQLConnection().connect();

	public static void main(String args[]) throws SQLException {
		boolean flag = true;
		System.out.println("Welcome to K L University CRT Selections \n");
		while(flag) {
			System.out.println("1. Register Student");
			System.out.println("2. Apply for CRT");
			System.out.println("3. Update Backlogs and CGPA");
			System.out.println("4. View CRT Eligible List");
			System.out.println("5. Quit\n");
			int ch = Integer.parseInt(sc.nextLine());
			switch(ch) {
			case 1: registerCRT();
					break;
			case 2: applyCRT();
					break;
			case 3: updateCGPA();
					break;
			case 4: getCRTList();
					break;
			case 5: System.out.println("BYE!");
					flag = false;
					System.exit(0);
					break;
			}
		}
	}

	private static void registerCRT() throws SQLException {
		PreparedStatement ps = con.prepareStatement("INSERT INTO student VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");

		System.out.println("Enter Student details : (ID, Name, Gender, Year, Department, CGPA, Backlogs) ");
		int id = Integer.parseInt(sc.nextLine());
		String name = sc.nextLine();
		String gender = sc.nextLine();
		int year = Integer.parseInt(sc.nextLine()); 
		String department = sc.nextLine();
		String mail = id+"@klunivesity.in";
		double cgpa = Double.parseDouble(sc.nextLine());
		int backlogs = Integer.parseInt(sc.nextLine());

		Student temp = new Student(id, name, gender, year, department, mail, cgpa, backlogs);
		
		ps.setInt(1, temp.getId());
		ps.setString(2, temp.getName());
		ps.setString(3, temp.getGender());
		ps.setInt(4, temp.getYear());
		ps.setString(5, temp.getDepartment());
		ps.setString(6, temp.getMail());
		ps.setDouble(7, temp.getCgpa());
		ps.setInt(8, temp.getBacklogs());
		ps.setString(9, temp.getEligibleCRT());


		int res = ps.executeUpdate();
		if(res > 0) {
			System.out.println("Student Registered SUCCESSFULLY!");
		} else {
			System.out.println("Execution Failed!");
		}
		
	}

	private static void applyCRT() throws SQLException {
		System.out.println("Enter Student ID to register for CRT ");
		int id = Integer.parseInt(sc.nextLine());
		PreparedStatement ps = con.prepareStatement("SELECT * FROM student WHERE id = "+id);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			int cgpa = rs.getInt(7);
			if(cgpa > 7.5) {
				PreparedStatement ps2 = con.prepareStatement("UPDATE student SET eligibleCRT=? where id=?");
				ps2.setString(1, "true");
				ps2.setInt(2, id);
				int res = ps2.executeUpdate();
				if(res > 0) {
					System.out.println("Student added to CRT");
				} else {
					System.out.println("Execution Failed2!");
				}
			} else {
				System.out.println("Student is not eligible for CRT\n");
			}
		} else {
			System.out.println("Student doesn't exist with ID : "+id+".\n");
		}
	}

	private static void updateCGPA() throws SQLException {
		System.out.println("Enter Student ID to update CGPA and Backlogs");
		int id = Integer.parseInt(sc.nextLine());
		PreparedStatement ps = con.prepareStatement("SELECT * FROM student where id=?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			System.out.println("Enter CGPA, Backlogs");
			double cgpa = Double.parseDouble(sc.nextLine());
			int backlogs = Integer.parseInt(sc.nextLine());
			PreparedStatement ps2 = con.prepareStatement("UPDATE student SET cgpa=?, backlogs=? WHERE id=?");
			ps2.setDouble(1, cgpa);
			ps2.setInt(2, backlogs);
			ps2.setInt(3, id);
			int res = ps2.executeUpdate();
			System.out.println("Student CGPA and Backlogs updated SUCCESSFULLY\n");
		} else {
			System.out.println("Student doesn't exist with ID : "+id+".\n");
		}
	}

	private static void getCRTList() throws SQLException {
		PreparedStatement ps = con.prepareStatement("SELECT * FROM student WHERE eligibleCRT=?");
		ps.setString(1, "true");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			System.out.println();
        	System.out.println("Id         : " + rs.getInt(1));
        	System.out.println("Name       : " + rs.getString(2));
        	System.out.println("Gender     : " + rs.getString(3));
        	System.out.println("Year       : " + rs.getInt(4));
        	System.out.println("Department : " + rs.getString(5));
        	System.out.println("KL Mail    : " + rs.getString(6));
        	System.out.println("CGPA       : " + rs.getDouble(7));
        	System.out.println("Backlogs   : " + rs.getInt(8));
        	System.out.println("Eligible   : " + rs.getString(9));
        	System.out.println();
		}
		
	}
}
