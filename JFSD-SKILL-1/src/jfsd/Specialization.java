package jfsd;

public class Specialization {

	private int specId;
	private String specName;
	private String specCode;
	private int slots;

	
	
	public Specialization(int specId, String specName, String specCode, int slots) {
		super();
		this.specId = specId;
		this.specName = specName;
		this.specCode = specCode;
		this.slots = slots;
	}
	
	public int getSpecId() {
		return specId;
	}
	public void setSpecId(int specId) {
		this.specId = specId;
	}
	public String getSpecName() {
		return specName;
	}
	public void setSpecName(String specName) {
		this.specName = specName;
	}
	public String getSpecCode() {
		return specCode;
	}
	public void setSpecCode(String specCode) {
		this.specCode = specCode;
	}
	public int getSlots() {
		return slots;
	}
	public void setSlots(int slots) {
		this.slots = slots;
	}
	public String toString() {
		return String.format("Specialization\t:\t%s\nID\t\t:\t%d\nSlots\t\t:\t%d\nCode\t\t:\t%s\n", this.getSpecName(), this.getSpecId(), this.getSlots(), this.getSpecCode());
	}

}
