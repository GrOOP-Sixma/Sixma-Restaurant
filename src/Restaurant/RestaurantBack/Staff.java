package Restaurant.RestaurantBack;

import Restaurant.Person;

public class Staff extends Person {
    private Gender gender;
	private int staffId;
    private static int nextStaffId = 1;
    private String role;

    // constructor
    public Staff(String name, Gender gender, String role) {
        super(name);
        this.gender = gender;
        staffId = getNextStaffId();
        this.role = role;
    }

    // getters
    public Gender getGender() {return gender;}
    public int getId() {return staffId;}
    public String getRole() {return role;}

    // setters
    public void setGender(Gender gender) {this.gender = gender;}
    public void setId(int staffId) {this.staffId = staffId;}
    public void setRole(String role) {this.role = role;}

    // methods
    private static int getNextStaffId() {
        return nextStaffId++;
    }
}