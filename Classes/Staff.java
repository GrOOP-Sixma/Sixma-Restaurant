public class Staff {

	private String name;
	private String gender;
	private int staffID;
	private String staffRole;

	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return this.gender;
	}

	/**
	 * 
	 * @param gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getStaffID() {
		return this.staffID;
	}

	/**
	 * 
	 * @param staffID
	 */
	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}

	public String getStaffRole() {
		return this.staffRole;
	}

	/**
	 * 
	 * @param staffRole
	 */
	public void setStaffRole(String staffRole) {
		this.staffRole = staffRole;
	}

}