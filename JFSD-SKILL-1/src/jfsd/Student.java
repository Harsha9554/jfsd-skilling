package jfsd;

public class Student {

	private String name;
	private int id;
	private String branch;
	private int specId;
	
	public Student(String name, int id, String branch, int specId) {
		super();
		this.name = name;
		this.id = id;
		this.branch = branch;
		this.specId = specId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public int getSpecId() {
		return specId;
	}
	public void setSpecId(int specId) {
		this.specId = specId;
	}
	
	
	public String toString() {
		return String.format("Name\t\t:\t%s\nID\t\t:\t%d\nBranch\t\t:\t%sSpecialization ID\t\t:%d\n", this.getName(),this.getId(),this.getBranch(), this.getSpecId());
	}
}
