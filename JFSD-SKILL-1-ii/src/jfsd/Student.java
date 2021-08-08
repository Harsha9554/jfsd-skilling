package jfsd;

public class Student {
	private int id;
	private String name;
	private String gender;
	private int year;
	private String department;
	private String mail;
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", gender=" + gender + ", year=" + year + ", department="
				+ department + ", mail=" + mail + ", cgpa=" + cgpa + ", backlogs=" + backlogs + "]";
	}
	private double cgpa;
	private int backlogs;
	private String eligibleCRT;
	
	public int getId() {
		return id;
	}
	public Student(int id, String name, String gender, int year, String department, String mail, double cgpa,
			int backlogs) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.year = year;
		this.department = department;
		this.mail = mail;
		this.cgpa = cgpa;
		this.backlogs = backlogs;
		this.eligibleCRT = "false";
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public double getCgpa() {
		return cgpa;
	}
	public void setCgpa(double cgpa) {
		this.cgpa = cgpa;
	}
	public int getBacklogs() {
		return backlogs;
	}
	public void setBacklogs(int backlogs) {
		this.backlogs = backlogs;
	}
	public String getEligibleCRT() {
		return eligibleCRT;
	}
	public void setEligibleCRT(String eligibleCRT) {
		this.eligibleCRT = eligibleCRT;
	}
	
}
