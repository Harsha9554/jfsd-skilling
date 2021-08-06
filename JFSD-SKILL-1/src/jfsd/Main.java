package jfsd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static Connection con = new MySQLConnection().connect();
	static List<Specialization> specializationList = new ArrayList<>();

	public static void main(String args[]) throws SQLException {
		getSpecializationList();
		boolean flag = true;
		System.out.println("Welcome to K L University Specialization Selection : \n");
		while(flag) {
			System.out.println("> Select Specialization : \n");
			for(Specialization s: specializationList)
				System.out.printf("%d. %s\n",s.getSpecId(), s.getSpecName());
			System.out.println("0. Quit\n");
			System.out.print("> ");
			int choice  = Integer.parseInt(sc.nextLine());
			if(choice == 0)
				flag = false;
			else inputStudent(choice);
			
		}
		
	}

	private static void inputStudent(int specId) throws SQLException {
		System.out.println("> Enter Student details : (Name, ID, Branch)\n");
		Specialization spec = getSpecialization(specId);
		if(checkSlot(spec)) {
			String name = sc.nextLine();
			int id = Integer.parseInt(sc.nextLine());
			String branch = sc.nextLine();
			addStudent(new Student(name, id, branch, specId), spec.getSlots());
		} else {
			System.out.printf("No Slots available for %s - (%s)\n",spec.getSpecName(), spec.getSpecCode());
		}
	}


	private static boolean checkSlot(Specialization spec) {
		return spec.getSlots() > 0;
	}

	private static Specialization getSpecialization(int specId) {
		for(Specialization s: specializationList)
			if(s.getSpecId() == specId)
				return s;
		return null;
	}

	private static void addStudent(Student s, int slots) throws SQLException {
		PreparedStatement st = con.prepareStatement("INSERT INTO student VALUES(?, ?, ?, ?)");
		st.setInt(1, s.getId());
		st.setString(2, s.getName());
		st.setString(3, s.getBranch());
		st.setInt(4, s.getSpecId());
		
		int i = st.executeUpdate();
		if(i > 0) {
			int newSlots = slots - 1;
			PreparedStatement st2 = con.prepareStatement("UPDATE specialization SET slots=? where spec_id=?");
			st2.setInt(1, newSlots);
			st2.setInt(2, s.getSpecId());
			int x = st2.executeUpdate();
			if(x > 0) {
				System.out.println("Student added SUCCESSFULLY!\n");
			} else {
				System.out.println("Student didn't add due to error");
			}
			
		}
		else
			System.out.println("Student didn't add due to error");
	}

	private static void getSpecializationList() throws SQLException {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM specialization");
		while(rs.next()) {
			Specialization temp = new Specialization(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
			specializationList.add(temp);
		}
	}
}
